package fr.tse.fise3.pri.p002.server.controller;

import fr.tse.fise3.pri.p002.server.dto.PostDTO;
import fr.tse.fise3.pri.p002.server.model.Author;
import fr.tse.fise3.pri.p002.server.model.Keyword;
import fr.tse.fise3.pri.p002.server.model.Post;
import fr.tse.fise3.pri.p002.server.service.PostConsumerService;
import fr.tse.fise3.pri.p002.server.service.PostProducerService;
import fr.tse.fise3.pri.p002.server.service.PostService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HomeController {


    @Autowired
    private PostProducerService postProducerService;
    @Autowired
    private PostConsumerService postConsumerService;
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private PostService postService;


    public HomeController() {
        Converter<Post, PostDTO> converter = context -> {
            PostDTO dto = new PostDTO();
            dto.setTitle(context.getSource().getTitle());
            dto.setAuthors(context.getSource().getAuthors().stream().map(Author::getAuthorName).collect(Collectors.toList()));
            dto.setAddress(context.getSource().getAddress());
            dto.setDate(context.getSource().getDate());
            dto.setUrl(context.getSource().getUrl());
            dto.setKeywords(context.getSource().getKeywords().stream().map(Keyword::getKeywordName).collect(Collectors.toList()));
            return dto;
        };
        modelMapper.createTypeMap(Post.class, PostDTO.class).setConverter(converter);
    }


    @GetMapping("/posts")
    public List<PostDTO> findAllPosts() {
        return postService.findAllPosts().stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList()).subList(0, 10);
    }

    @GetMapping("/posts/search")
    public List<PostDTO> findPostsByTileLike(@RequestParam String tag, @RequestParam String value) {
        switch (tag) {
            case "title":
                return postService.findPostByTitleLike(value).stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

            case "author":
                return postService.findByAuthors_authorNameContaining(value).stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
            case "keywords":
                return postService.findByKeywords_keywordNameContaining(value).stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }


    @GetMapping("/start")
    public String start() {

        BlockingQueue<Post> postBlockingQueue = new ArrayBlockingQueue<Post>(100);
        Thread postProducerThread = new Thread(postProducerService);
        Thread postConsumerThread = new Thread(postConsumerService);

        postProducerThread.start();
        postConsumerThread.start();

        return "Start";


    }


}
