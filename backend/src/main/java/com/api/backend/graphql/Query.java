package com.api.backend.graphql;

import com.api.backend.models.*;
import com.api.backend.repositories.*;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Iterables;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public Query(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public Optional<Post> post(Long id) {
        return postRepository.findById(id);
    }

    public Iterable<Post> posts() {
        return postRepository.findAll();
    }

    public Iterable<Comment> postComments(Long postId) {
        Iterable<Comment> postComments =  commentRepository.findAll();
        return Iterables.filter(postComments, comment -> {
            return comment.getPost().getId() == postId;
        });
    }
}
