package com.api.backend.graphql;

import com.api.backend.models.*;
import com.api.backend.repositories.CommentRepository;
import com.api.backend.repositories.PostRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    public Mutation(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // Posts
    public Post createPost(String title, String body){
        Post post = new Post(title, body);
        postRepository.save(post);
        return post;
    }

    public Optional<Post> updatePost(Long id, String title, String body) {
        Optional<Post> post = postRepository.findById(id);
        post.ifPresent(a -> {
            a.setTitle(title);
            a.setBody(body);
            postRepository.save(a);
        });
        return post;
    }

    public boolean deletePost(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    // Comments
    public Comment createComment(String text, Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        Comment comment = new Comment(text);
        comment.setPost(post);
        commentRepository.save(comment);
        return comment;
    }

    public boolean deleteComment(Long id) {
        commentRepository.deleteById(id);
        return true;
    }
}
