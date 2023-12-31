package me.niallmurray.slipstream.web;

import me.niallmurray.slipstream.domain.User;
import me.niallmurray.slipstream.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@SuppressWarnings("SameReturnValue")
@Controller
public class DashboardController {
  @Autowired
  private UserService userService;


  @GetMapping("/dashboard")
  public String redirectUserDashboard(@AuthenticationPrincipal User userAuth) {
    return "redirect:/dashboard/" + userAuth.getUserId();
  }

  @GetMapping("/dashboard/{userId}")
  public String getDashboard(@PathVariable Long userId, ModelMap modelMap) {
    User user = userService.findById(userId);
    if (!userService.isAdmin(user)) {
      modelMap.addAttribute("isAdmin", false);
    }

    modelMap.addAttribute("user", user);
    modelMap.addAttribute("timeToPick", false);

    if (userService.isLoggedIn(user)) {
      modelMap.addAttribute("isLoggedIn", true);
    }
    if (userService.isAdmin(user)) {
      modelMap.addAttribute("isAdmin", true);

    }

    return "dashboard";
  }
}


