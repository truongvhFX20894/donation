package com.example.springboot.thymeleafdemo.controller;

import com.example.springboot.thymeleafdemo.model.Donation;
import com.example.springboot.thymeleafdemo.model.Role;
import com.example.springboot.thymeleafdemo.model.User;
import com.example.springboot.thymeleafdemo.model.UserDonation;
import com.example.springboot.thymeleafdemo.service.DonationService;
import com.example.springboot.thymeleafdemo.service.RoleService;
import com.example.springboot.thymeleafdemo.service.UserDonationService;
import com.example.springboot.thymeleafdemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private DonationService donationService;
    private UserDonationService userDonationService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, DonationService donationService, UserDonationService userDonationService, RoleService roleService) {
        this.userService = userService;
        this.donationService = donationService;
        this.userDonationService = userDonationService;
        this.roleService = roleService;
    }

    @GetMapping("/home")
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/showAccountPage")
    public String showAccountPage(Model model) {
        List<User> userList = userService.findAll();
        List<Role> roleList = roleService.findAll();
        model.addAttribute("users", userList);
        model.addAttribute("roleList", roleList);
        return "admin/account";
    }

    @GetMapping("/showDonationPage")
    public String showDonatinPage(Model model) {
        List<Donation> donationList = donationService.findAll();
        model.addAttribute("donations", donationList);
        return "admin/donation";
    }

// CÁC CHỨC NĂNG QUẢN LÝ TÀI KHOẢN
    @PostMapping("/addNewUser")
    public String addNewUser(@ModelAttribute("newUser") User user, @RequestParam("roleId") int roleId, RedirectAttributes redirectAttributes) {
        Role role = roleService.findById(roleId);
        user.setRole(role);
        user.setStatus(1);
        userService.save(user);
        return "redirect:/admin/showAccountPage";
    }

    @PostMapping("/updateUser")
    public String updateUser(HttpServletRequest request, @ModelAttribute("updateUser") User user, @RequestParam("idRole") int roleId) {
        Role role = roleService.findById(roleId);
        user.setRole(role);
        userService.save(user);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/lockUser")
    public String lockUser(@RequestParam("userId") int id, HttpServletRequest request) {
        User user = userService.findById(id);
        user.setStatus(0);
        userService.save(user);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/unlockUser")
    public String unlockUser(@RequestParam("userId") int id, HttpServletRequest request) {
        User user = userService.findById(id);
        user.setStatus(1);
        userService.save(user);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id, HttpServletRequest request) {
        userService.deleteById(id);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/sendMail")
    public String sendEmail(@RequestParam("userId") int id, @RequestParam("note") String note, HttpServletRequest request) {
        User tempUser = userService.findById(id);
        tempUser.setNote(note);
        userService.save(tempUser);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }


// CÁC CHỨC NĂNG QUẢN LÝ ĐỢT QUYÊN GÓP
    @PostMapping("/addNewDonation")
    public String addNewUser(@ModelAttribute("newDonation") Donation donation, RedirectAttributes redirectAttributes) {
        donation.setStatus(1); // set trạng thái của đợt quyên góp là Mới tạo
        donationService.save(donation);
        return "redirect:/admin/showDonationPage";
    }

    @PostMapping("/updateDonation")
    public String updateDonation(@ModelAttribute("updatedDonation") Donation donation,HttpServletRequest request) {
        Donation tempDonation = donationService.findById(donation.getId());
        tempDonation.setCode(donation.getCode());
        tempDonation.setName(donation.getName());
        tempDonation.setStartDate(donation.getStartDate());
        tempDonation.setEndDate(donation.getEndDate());
        tempDonation.setOrganizationName(donation.getOrganizationName());
        tempDonation.setPhoneNumber(donation.getPhoneNumber());
        tempDonation.setDescription(donation.getDescription());
        donationService.save(tempDonation);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/deleteDonation")
    public String deleteDonation(@RequestParam("donationId") int id, HttpServletRequest request) {
        donationService.deleteById(id);
        userDonationService.deleteByDonationId(id);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/makeDonation") // chuyển đợt quyên góp sang trạng thái quyên góp
    public String makeDonation(@RequestParam("id") int id, HttpServletRequest request) {
        Donation tempDonation = donationService.findById(id);
        tempDonation.setStatus(2);
        donationService.save(tempDonation);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/endDonation") // chuyển đợt quyên góp sang trạng thái kết thúc quyên góp
    public String endDonation(@RequestParam("id") int id, HttpServletRequest request) {
        Donation tempDonation = donationService.findById(id);
        tempDonation.setStatus(3);
        donationService.save(tempDonation);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("closeDonation") // chuyển đợt quyên góp sang trạng thái đóng quyên góp
    public String closeDonation(@RequestParam("id") int id, HttpServletRequest request) {
        Donation tempDonation = donationService.findById(id);
        tempDonation.setStatus(0);
        donationService.save(tempDonation);
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

    @GetMapping("/donationInfo/{donationId}")  // thông tin 1 đợt quyên góp cụ thể
    public String donationInfo(@PathVariable int donationId, Model model) {
        Donation tempDonation = donationService.findById(donationId);
        model.addAttribute("donation", tempDonation);
        return "admin/detail";
    }

    @PostMapping("/acceptDonation") // xét duyệt lượt quyên góp
    public String acceptDonation(@RequestParam("id") int id, HttpServletRequest http) {
        UserDonation userDonation = userDonationService.findById(id);

        // xét duyệt lượt quyên góp (trạng thái 0 là đã duyệt)
        userDonation.setStatus(0);
        userDonationService.save(userDonation);


        //cập nhật số tiền từ thiện
        int donationId = userDonation.getDonationId();
        Donation tempDonation = donationService.findById(donationId);
        int newMoney = tempDonation.getMoney()+userDonation.getMoney();
        tempDonation.setMoney(newMoney);
        donationService.save(tempDonation);

        String referer = http.getHeader("Referer");
        return "redirect:"+referer;
    }

    @PostMapping("/cancelDonation") // từ chối lượt quyên góp
    public String cancelDonation(@RequestParam("id") int id, HttpServletRequest http) {
        userDonationService.deleteById(id);

        String referer = http.getHeader("Referer");
        return "redirect:"+referer;
    }
}
