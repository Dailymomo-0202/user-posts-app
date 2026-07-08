package com.yaochen.user_posts_app.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.yaochen.user_posts_app.DTO.PostApiResponse;
import com.yaochen.user_posts_app.DTO.UserApiResponse;
import com.yaochen.user_posts_app.Entity.Post;
import com.yaochen.user_posts_app.Entity.User;
import com.yaochen.user_posts_app.Repository.PostRepository;
import com.yaochen.user_posts_app.Repository.UserRepository;

@Service
public class DataSyncService {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // inject dependencies via constructor
    public DataSyncService(RestTemplate restTemplate, UserRepository userRepository, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // buisness logic to sync data from API to database
    public void syncData() {
        // Fetch users from the API
        UserApiResponse[] users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users",
                UserApiResponse[].class);
        if (users != null) {
            // Retrieve each user from the API response and save it to the database
            for (UserApiResponse userApiResponse : users) {
                User user = new User();
                user.setId(userApiResponse.getId());
                user.setName(userApiResponse.getName());
                user.setUsername(userApiResponse.getUsername());
                user.setEmail(userApiResponse.getEmail());

                user.setPhone(userApiResponse.getPhone());
                user.setWebsite(userApiResponse.getWebsite());

                user.setCompanyName(userApiResponse.getCompany().getName());
                user.setStreet(userApiResponse.getAddress().getStreet());
                user.setSuite(userApiResponse.getAddress().getSuite());
                user.setCity(userApiResponse.getAddress().getCity());
                user.setZipcode(userApiResponse.getAddress().getZipcode());
                userRepository.save(user);
            }
        }

        // Fetch posts from the API
        PostApiResponse[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",
                PostApiResponse[].class);
        //check that posts is not null before iterating over it to avoid NullPointerException
        if (posts != null) {
            for (PostApiResponse postApiResponse : posts) {
                Post post = new Post();
                post.setId(postApiResponse.getId());
                post.setTitle(postApiResponse.getTitle());
                post.setBody(postApiResponse.getBody());

                // Find the associated user by userId and set it to the post
                User user = userRepository.findById(postApiResponse.getUserId()).orElse(null);
                post.setUser(user);

                postRepository.save(post);
            }
        }
    }

}
