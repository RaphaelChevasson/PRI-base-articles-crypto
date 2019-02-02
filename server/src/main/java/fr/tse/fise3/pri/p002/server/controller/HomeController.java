package fr.tse.fise3.pri.p002.server.controller;

import fr.tse.fise3.pri.p002.server.dto.DataSourceDTO;
import fr.tse.fise3.pri.p002.server.dto.PostDTO;
import fr.tse.fise3.pri.p002.server.model.Author;
import fr.tse.fise3.pri.p002.server.model.DataSource;
import fr.tse.fise3.pri.p002.server.model.Keyword;
import fr.tse.fise3.pri.p002.server.model.Post;
import fr.tse.fise3.pri.p002.server.service.*;
import fr.tse.fise3.pri.p002.server.thread.HalApiRequestThread;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class HomeController {


    @Autowired
    private EPrintPostProducerService EPrintPostProducerService;
    @Autowired
    private EPrintPostConsumerService EPrintPostConsumerService;
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private PostService postService;
    @Autowired
    private HalApiService halApiService;
    @Autowired
    private DataSourceService dataSourceService;

    private Converter<Post, PostDTO> converter;


    public HomeController() {
        converter = context -> {
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
    public Page<PostDTO> findAllPosts(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postService.findAllPosts(pageable).map(post -> modelMapper.map(post, PostDTO.class));
    }

    @GetMapping("/posts/search")
    public Page<PostDTO> findPostsByTileLike(@RequestParam String tag, @RequestParam String value, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        switch (tag) {
            case "title":
                return postService.findPostByTitleLike(value, pageable).map(post -> modelMapper.map(post, PostDTO.class));

            case "author":
                return postService.findByAuthors_authorNameContaining(value, pageable).map(post -> modelMapper.map(post, PostDTO.class));
            case "keywords":
                return postService.findByKeywords_keywordNameContaining(value, pageable).map(post -> modelMapper.map(post, PostDTO.class));
            default:
                return new PageImpl<>(Collections.emptyList());
        }
    }

    @GetMapping("/hal/info")
    public DataSourceDTO getHalSourceInfo() {

        DataSource halDataSource = dataSourceService.getHalDataSource();

        DataSourceDTO dataSourceDTO = new DataSourceDTO();
        dataSourceDTO.setTotal(halDataSource.getTotal());
        dataSourceDTO.setCurrentOffset(halDataSource.getCurrentOffset());
        dataSourceDTO.setCreateDate(halDataSource.getCreateDate());
        dataSourceDTO.setModifyDate(halDataSource.getModifyDate());

        dataSourceDTO.setStatus(HalApiRequestThread.isRunning());

        return dataSourceDTO;
    }

    @GetMapping("/eprint/info")
    public DataSourceDTO getEPrintSourceInfo() {

        DataSource ePrintDataSource = dataSourceService.getEPrintDataSource();

        DataSourceDTO dataSourceDTO = new DataSourceDTO();
        dataSourceDTO.setTotal(ePrintDataSource.getTotal());
        dataSourceDTO.setCurrentOffset(ePrintDataSource.getCurrentOffset());
        dataSourceDTO.setCreateDate(ePrintDataSource.getCreateDate());
        dataSourceDTO.setModifyDate(ePrintDataSource.getModifyDate());
//        dataSourceDTO.setStatus(HalApiRequestThread.isRunning());

        return dataSourceDTO;
    }


    @GetMapping("/start")
    public String start() {

        if (!HalApiRequestThread.isRunning()) {
            BlockingQueue<Post> postBlockingQueue = new ArrayBlockingQueue<Post>(100);
            Thread postProducerThread = new Thread(EPrintPostProducerService);
            Thread postConsumerThread = new Thread(EPrintPostConsumerService);
            postProducerThread.start();
            postConsumerThread.start();
            halApiService.start();
            return "Start";

        } else {
            return "is already running";
        }


    }


}
