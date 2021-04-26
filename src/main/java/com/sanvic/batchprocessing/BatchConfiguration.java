package com.sanvic.batchprocessing;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.sanvic.model.MatchDataCSVEntity;
import com.sanvic.model.MatchDataDBEntity;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private final String []  FIELDS_NAMES = new String[]{
			"id","city","date","player_of_match","venue","neutral_venue","team1","team2","toss_winner",
			"toss_decision","winner","result","result_margin","eliminator","method","umpire1","umpire2"
			};
	
	
	 @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  
	  @Bean
	  public FlatFileItemReader<MatchDataCSVEntity> reader() {
	    return new FlatFileItemReaderBuilder<MatchDataCSVEntity>()
	      .name("matchItemReader")
	      .resource(new ClassPathResource("match-data.csv"))
	      .delimited()
	      .names(FIELDS_NAMES)
	      .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchDataCSVEntity>() {{
	        setTargetType(MatchDataCSVEntity.class);
	      }})
	      .build();
	  }

	  @Bean
	  public MatchBatchProcessor processor() {
	    return new MatchBatchProcessor();
	  }

	  @Bean
	  public JdbcBatchItemWriter<MatchDataDBEntity> writer(DataSource dataSource) {
	    return new JdbcBatchItemWriterBuilder<MatchDataDBEntity>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT INTO MATCH_DTLS (id,city,date,player_of_match,venue,team1,team2,toss_winner,toss_decision,match_winner,result,result_margin,eliminator,method,umpire1,umpire2) "
	      							+ "VALUES (:id,:city,:date,:playerOfMatch,:venue,:team1,:team2,:tossWinner,:tossDecision,:matchWinner,:result,:resultMargin,:eliminator,:method,:umpire1,:umpire2)")
	      .dataSource(dataSource)
	      .build();
	  }
	  
	  
	  @Bean
	  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1(JdbcBatchItemWriter<MatchDataDBEntity> writer) {
	    return stepBuilderFactory.get("step1")
	      .<MatchDataCSVEntity, MatchDataDBEntity> chunk(10)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer)
	      .build();
	  }
}
