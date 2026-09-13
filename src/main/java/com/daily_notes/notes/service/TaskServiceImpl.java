package com.daily_notes.notes.service;

import com.daily_notes.notes.dao.TaskDaoService;
import com.daily_notes.notes.dto.TaskDto;
import com.daily_notes.notes.entity.TaskEntity;
import com.daily_notes.notes.mapper.CustomMapper;
import com.daily_notes.notes.records.ApiResponse;
import com.daily_notes.notes.util.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDaoService taskDaoService;
    Logger logger = LoggerFactory.getLogger("");

    @Override
    public ApiResponse createTask(String userId, TaskDto taskDto) {
        TaskEntity taskEntity = CustomMapper.mapToEntity(taskDto, TaskEntity.class);
        taskEntity.setId(IdGenerator.generateId());
        taskEntity.setUserId(userId);
        taskDaoService.createTask(taskEntity);
        logger.info("Task created for user {}: {}", userId, taskDto);
        return new ApiResponse().success(Boolean.TRUE).message("Success");
    }

    @Override
    public ApiResponse getAllTasksByUserId(String userId, Integer limit, String lastId, String search) {
        Page<TaskEntity> tasks = taskDaoService.getAllTasksByUserId(userId, limit, lastId, search);
        logger.info("Retrieved all tasks for user {}", userId);
        return new ApiResponse()
                .success(Boolean.TRUE)
                .message("Success")
                .data(tasks.items());
    }

}
