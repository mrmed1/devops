package com.isamm.tasks;

import com.isamm.tasks.models.Label;
import com.isamm.tasks.models.Member;
import com.isamm.tasks.models.Project;
import com.isamm.tasks.models.Task;
import com.isamm.tasks.repository.LabelRepository;
import com.isamm.tasks.repository.MemberRepository;
import com.isamm.tasks.repository.ProjectRepository;
import com.isamm.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class TaskManagementApplication implements CommandLineRunner {
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private LabelRepository labelRepository;
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
	@Override
	public void run(String... args) throws Exception {
		// Create two Members
		Member member1 = new Member();
		member1.setUsername("JohnDoe");
		member1.setPassword("password1");

		Member member2 = new Member();
		member2.setUsername("JaneSmith");
		member2.setPassword("password2");

		// Create a Project
		Project project = new Project();
		project.setName("Sample Project");
		project.setDescription("This is a sample project");

		Project project2 = new Project();
		project2.setName("Sample Project 2");
		project2.setDescription("This is a sample project 2");
		// Create two Tasks
		Task task1 = new Task();
		task1.setTitle("Task 1");
		task1.setDescription("Description for Task 1");
		task1.setStartDate(LocalDate.now());
		task1.setDueDate(LocalDate.now().plusDays(7));
		task1.setCompleted(false);

		Task task2 = new Task();
		task2.setTitle("Task 2");
		task2.setDescription("Description for Task 2");
		task2.setStartDate(LocalDate.now());
		task2.setDueDate(LocalDate.now().plusDays(7));
		task2.setCompleted(false);

		// Create two Labels
		Label label1 = new Label();
		label1.setName("Label 1");

		Label label2 = new Label();
		label2.setName("Label 2");

		// Establish relationships
		project.setMembers(Arrays.asList(member1, member2));

		task1.setProject(project);
		task2.setProject(project);

		Set<Task> tasks = new HashSet<>(Arrays.asList(task1, task2));
		label1.setTasks(tasks);
		label2.setTasks(tasks);

		// Save entities
		memberRepository.saveAll(Arrays.asList(member1, member2));
		projectRepository.save(project);
		projectRepository.save(project2);
		taskRepository.saveAll(Arrays.asList(task1, task2));
		labelRepository.saveAll(Arrays.asList(label1, label2));
	}
}
