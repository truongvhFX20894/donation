package com.example.springboot.thymeleafdemo.controller;

import com.example.springboot.thymeleafdemo.model.Donation;
import com.example.springboot.thymeleafdemo.model.UserDonation;
import com.example.springboot.thymeleafdemo.service.DonationService;
import com.example.springboot.thymeleafdemo.service.UserDonationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {
    private DonationService donationService;
    private UserDonationService userDonationService;

    @Autowired
    public DonationController(DonationService donationService, UserDonationService userDonationService) {
        this.donationService = donationService;
        this.userDonationService = userDonationService;
    }

    // Trang chủ hiện thông tin các đợt từ thiện
    @GetMapping("/list")
    public String listDonation(Model model) {
        List<Donation> donationList = donationService.findAll();
        model.addAttribute("donations", donationList);
        return "public/home";
    }

    //Thông tin chi tiết của một đợt từ thiện
    @GetMapping("/showDetailDonation")
    public String showDetailDonation(@RequestParam("donationId") int id, Model model) {
        Donation donation = donationService.findById(id);
        model.addAttribute("donation", donation);
        return "public/detail";
    }

    //Thêm 1 lần từ thiện mới
    @PostMapping("/addNewUserDonation")
    public String addNewUserDonation(@ModelAttribute("userDonation") UserDonation userDonation, RedirectAttributes result, HttpServletRequest request) {
        //yêu cầu người dùng phải đăng nhập để sử dụng chức năng quyên góp
        if (userDonation.getUserId()==0) {
            result.addFlashAttribute("loginRequire", "loginRequire");
            return "redirect:/donation/list";
        }
        // khởi tạo thông tin của lượt từ thiện mới nhưng chưa xét duyệt trên hệ thống
        // trạng thái 1 là đã quyên góp nhưng chưa duyệt qua
        userDonation.setStatus(1);
        userDonationService.save(userDonation);

        //Tạo thông báo sau khi đã từ thiện thành công
        result.addFlashAttribute("msg", "msg");
        String referer = request.getHeader("Referer");
        return "redirect:"+referer;
    }

}
