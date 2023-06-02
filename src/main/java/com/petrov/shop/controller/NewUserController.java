//package com.petrov.shop.controller;
//
//import com.petrov.shop.dto.RoleDto;
//import com.petrov.shop.dto.UserDto;
//import com.petrov.shop.service.RoleService;
//import com.petrov.shop.service.UserService;
//import com.petrov.shop.utils.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.petrov.shop.utils.Converter.convertCarUser;
//import static com.petrov.shop.utils.Mapper.mapRoles;
//import static com.petrov.shop.utils.Mapper.mapUser;
////import static com.petrov.shop.utils.Mapper.mapUserAuth;
//
//@RestController
//public class NewUserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleService roleService;
//
//
//    @GetMapping("/new_user")
//    public String newUser(Model model) {
//        List<RoleDto> roles = mapRoles(roleService.getAll());
//        model.addAttribute("user", new UserDto());
//        model.addAttribute("roles", roles);
//        return "new_user";
//    }
//
//    @PostMapping("/new_user")
//    public String update(UserDto userDto, BindingResult result, Model model) {
//        List<RoleDto> roles = mapRoles(roleService.getAll());
//
//        if (result.hasErrors()) {
//            model.addAttribute("roles", roles);
//            return "new_user";
//        }
//
//        if (!userDto.getPassword().equals(userDto.getPasswordRpt())) {
//            model.addAttribute("roles", roles);
//            result.rejectValue("passwordRpt", "", "Password is wrong");
//            return "new_user";
//        }
//
//        List<UserDto> users = userService.findAll().stream()
//                .map(Mapper::mapUser)
//                .collect(Collectors.toList());
//        for (UserDto user : users) {
//            if (user.getLogin().equals(userDto.getLogin())) {
//                model.addAttribute("roles", roles);
//                result.rejectValue("username", "", "Username is already exist");
//                return "new_user";
//            }
//        }
//        userService.saveOrUpdate(convertCarUser(userDto));
//        return "redirect:/login";
//    }
//
//    @ExceptionHandler
//    public ModelAndView notFoundExceptionHandler(ChangeSetPersister.NotFoundException e) {
//        ModelAndView modelAndView = new ModelAndView("not_found");
//        modelAndView.addObject("message", e.getMessage());
//        modelAndView.setStatus(HttpStatus.NOT_FOUND);
//        return modelAndView;
//    }
//}
