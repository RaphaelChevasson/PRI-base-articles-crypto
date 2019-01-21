package fr.tse.fise3.pri.p002.server.controller;

import fr.tse.fise3.pri.p002.server.model.Post;
import fr.tse.fise3.pri.p002.server.service.PostConsumerService;
import fr.tse.fise3.pri.p002.server.service.PostProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PostProducerService postProducerService;
    @Autowired
    private PostConsumerService postConsumerService;


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
