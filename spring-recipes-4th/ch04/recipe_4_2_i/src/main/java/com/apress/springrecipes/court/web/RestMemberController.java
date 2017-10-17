package com.apress.springrecipes.court.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apress.springrecipes.court.domain.Members;
import com.apress.springrecipes.court.service.MemberService;

@Controller
public class RestMemberController {

    private final MemberService memberService;

    @Autowired
    public RestMemberController(MemberService memberService) {
        super();
        this.memberService=memberService;
    }

    @RequestMapping("/members")
    public String getRestMembersJson(Model model) {
        // Return view jsonmembertemplate. Via resolver the view
        // will be mapped to a jackson ObjectMapper bound to the Member class

        Members members = new Members();
        members.addMembers(memberService.findAll());
        model.addAttribute("members", members);
        return "jsonmembertemplate";
    }
}
