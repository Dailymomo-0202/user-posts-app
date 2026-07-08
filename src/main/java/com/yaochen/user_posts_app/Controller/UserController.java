package com.yaochen.user_posts_app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yaochen.user_posts_app.Entity.User;
import com.yaochen.user_posts_app.Repository.PostRepository;
import com.yaochen.user_posts_app.Repository.UserRepository;
import com.yaochen.user_posts_app.Service.DataSyncService;

@Controller
public class UserController {
        // Controller methods will go here
    private final DataSyncService dataSyncService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

        public UserController(DataSyncService dataSyncService, 
                            UserRepository userRepository, 
                            PostRepository postRepository) {
            this.dataSyncService = dataSyncService;
            this.userRepository = userRepository;
            this.postRepository = postRepository;
        }
        
        @GetMapping("/users")
        //List all users
        public String listUsers(Model model) { //model is used to pass data to the view
            // Check if the database is empty, if so, sync data from the API
            if (userRepository.count() == 0) {
                dataSyncService.syncData();
            }
            // Retrieve all users from the database
            model.addAttribute("users", userRepository.findAll());
            return "users"; // Return the view name (users.html) //just html not user instance
        }

        @GetMapping("/users/{userId}/posts")
        //List related posts
        public String listPosts(@PathVariable Long userId, Model model) {
            User user = userRepository.findById(userId).orElseThrow();
            model.addAttribute("user", user);
            model.addAttribute("posts", postRepository.findByUserId(userId));
            return "user-detail";
            
        }



    }
