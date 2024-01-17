package com.example.springboot.thymeleafdemo.controller;

import com.example.springboot.thymeleafdemo.model.User;
import com.example.springboot.thymeleafdemo.service.DonationService;
import com.example.springboot.thymeleafdemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/user")
public class LoginController {
    private DonationService donationService;
    private UserService userService;

    @Autowired
    public LoginController(DonationService donationService, UserService userService) {
        this.donationService = donationService;
        this.userService = userService;
    }

//    @GetMapping("/home")
//    public String showHome(Model model) {
//        List<Donation> donationList = donationService.findAll();
//        model.addAttribute("donations", donationList);
//        return "public/home";
//    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/processFormLogin")
    public String processFormLogin(@RequestParam("email") String email, @RequestParam("password") String password,
                                   HttpSession session, RedirectAttributes result, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("password", password);

        User user = userService.findUserByEmailAndPassword(email, password);
        // truyền thông tin User vào phiên đăng nhập
        if (user!=null && user.getStatus()!=0) {
            if (Objects.equals(user.getRole().getRoleName(), "USER")) {
                session.setAttribute("user", user);
                return "redirect:/donation/list";
            } else {
                session.setAttribute("admin", user);
                return "redirect:/admin/home";
            }
        } else if (user!=null && user.getStatus()==0) {
            result.addFlashAttribute("lock", "Lock");
            return "redirect:/user/login";
        } else {
                result.addFlashAttribute("error", "Error");
                return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session = request.getSession();
        session.invalidate();
        // trả về trang chủ lúc chưa đăng nhập
        return "redirect:/donation/list";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/info")
    public String infoOfUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (Objects.equals(user.getRole().getRoleName(), "ADMIN")) {
            model.addAttribute("admin", "ADMIN");
        }
        return "public/info";
    }

}
