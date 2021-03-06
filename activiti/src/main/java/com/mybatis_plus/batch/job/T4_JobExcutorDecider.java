package com.mybatis_plus.batch.job;

import com.mybatis_plus.batch.pojo.MyJobExecutionDecider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class T4_JobExcutorDecider {
    /**
     * 决策器,得加@bean
     */

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job deciderJob() {
        return jobBuilderFactory.get("decider-job3")
                .start(step1())
                .next(jobExecutionDecider())
                .from(jobExecutionDecider()).on("aaa").to(step2())
                .from(jobExecutionDecider()).on("bbb").to(step3())
                .from(step3()).on("*").to(jobExecutionDecider())
                .end()
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("decider-step-1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("============>decider-step-1执行");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    public Step step2() {
        return stepBuilderFactory.get("decider-step-2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("============>decider-step-2执行");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    public Step step3() {
        return stepBuilderFactory.get("decider-step-3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("============>decider-step-3执行");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    /**
     * 决策器,得加@bean，原因：同一个bean???
     */
    @Bean
    public JobExecutionDecider jobExecutionDecider() {
        return new MyJobExecutionDecider();
    }
}
