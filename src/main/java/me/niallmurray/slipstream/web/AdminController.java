package me.niallmurray.slipstream.web;

import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.security.ActiveUserStore;
import me.niallmurray.slipstream.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
  @Autowired
  private ActiveUserStore activeUserStore;
  @Autowired
  private AdminService adminService;

  @GetMapping("/admin")
  public String getAdmin(ModelMap modelMap) {
    List<User> allUserAccounts = adminService.getAllUserAccounts();
    modelMap.addAttribute("users", allUserAccounts);
    modelMap.addAttribute("activeUsers", activeUserStore.getUsers());
    modelMap.addAttribute("isLoggedIn", true);
    modelMap.addAttribute("isAdmin", true);
    return "admin";
  }

}