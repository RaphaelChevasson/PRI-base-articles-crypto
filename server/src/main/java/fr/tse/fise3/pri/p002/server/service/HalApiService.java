package fr.tse.fise3.pri.p002.server.service;

import fr.tse.fise3.pri.p002.server.thread.HalApiRequestThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class HalApiService {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    public HalApiService() {

    }

    public void start(){
        HalApiRequestThread halApiRequestThread = applicationContext.getBean(HalApiRequestThread.class);
        taskExecutor.execute(halApiRequestThread);
    }

}
