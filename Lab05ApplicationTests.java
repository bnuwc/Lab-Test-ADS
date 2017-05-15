package camt.cbsd;

import camt.cbsd.dao.CourseDao;
import camt.cbsd.dao.StudentDao;
import camt.cbsd.entity.Course;
import camt.cbsd.entity.Student;
import camt.cbsd.entity.security.User;
import camt.cbsd.services.CourseServiceImpl;
import camt.cbsd.services.StudentService;
import camt.cbsd.services.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Lab05ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	CourseDao courseDao;
	@Test
	public void testGetCourseCount(){
		CourseServiceImpl courseService = new CourseServiceImpl();
		courseService.setCourseDao(courseDao);
		assertThat(courseService.getCourseCount(),is(3));
	}
	@Autowired
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	StudentDao studentDao;
	StudentServiceImpl studentService;

	@Test
	public void testGetUserName() {
		String imageBaseUrl = "http://localhost:4200/student/images/";

		Student student1 = Student.builder().studentId("SE-001").name("Mitsuha").surname("Miyamizu")
				.gpa(2.15).image(imageBaseUrl+"mitsuha.gif").feature(true)
				.penAmount(0).description("The most beloved one").build();
		Student student2 = Student.builder().studentId("SE-002").name("Prayuth").surname("The minister")
				.gpa(3.59).image(imageBaseUrl+"tu.jpg").feature(false)
				.penAmount(15).description("The great man ever!!!!").build();
		Student student3 = Student.builder().studentId("SE-003").name("Jurgen").surname("Kloop")
				.gpa(2.15).image(imageBaseUrl+"Kloop.gif").feature(true)
				.penAmount(2).description("The man for the Kop").build();

		studentService = new StudentServiceImpl();
		studentService.setStudentDao(studentDao);

		assertThat(studentService.getUsername("davy"), is(nullValue()));

		assertThat(studentService.getUsername("admin").getStudentId(), is(student1.getStudentId()));
		assertThat(studentService.getUsername("admin").getName(), is(student1.getName()));
		assertThat(studentService.getUsername("admin").getSurname(), is(student1.getSurname()));
		assertThat(studentService.getUsername("admin").getGpa(), is(student1.getGpa()));
		assertThat(studentService.getUsername("admin").getImage(), is(student1.getImage()));
		assertThat(studentService.getUsername("admin").isFeature(), is(student1.isFeature()));
		assertThat(studentService.getUsername("admin").getPenAmount(), is(student1.getPenAmount()));
		assertThat(studentService.getUsername("admin").getDescription(), is(student1.getDescription()));

		assertThat(studentService.getUsername("user").getStudentId(), is(student2.getStudentId()));
		assertThat(studentService.getUsername("user").getName(), is(student2.getName()));
		assertThat(studentService.getUsername("user").getSurname(), is(student2.getSurname()));
		assertThat(studentService.getUsername("user").getGpa(), is(student2.getGpa()));
		assertThat(studentService.getUsername("user").getImage(), is(student2.getImage()));
		assertThat(studentService.getUsername("user").isFeature(), is(student2.isFeature()));
		assertThat(studentService.getUsername("user").getPenAmount(), is(student2.getPenAmount()));
		assertThat(studentService.getUsername("user").getDescription(), is(student2.getDescription()));

		assertThat(studentService.getUsername("disabled").getStudentId(), is(student3.getStudentId()));
		assertThat(studentService.getUsername("disabled").getName(), is(student3.getName()));
		assertThat(studentService.getUsername("disabled").getSurname(), is(student3.getSurname()));
		assertThat(studentService.getUsername("disabled").getGpa(), is(student3.getGpa()));
		assertThat(studentService.getUsername("disabled").getImage(), is(student3.getImage()));
		assertThat(studentService.getUsername("disabled").isFeature(), is(student3.isFeature()));
		assertThat(studentService.getUsername("disabled").getPenAmount(), is(student3.getPenAmount()));
		assertThat(studentService.getUsername("disabled").getDescription(), is(student3.getDescription()));
	}
	@Test
	public void TestQueryStudent(){
		List<Student> stuList = new ArrayList<>();
		List<Course> courseList = new ArrayList<>();
		List<Student> testBlank = new ArrayList<>();
		List<Student> testquery = new ArrayList<>();

		testquery.add(new Student(1, "SE-001", "Mitsuha", "Miyamizu", 2.15, "...", true, 0, "The most beloved one", courseList, new User()));

		stuList.add(new Student(1, "SE-001", "Mitsuha", "Miyamizu", 2.15, "...", true, 0, "The most beloved one", courseList, new User()));
		stuList.add(new Student(2, "SE-002", "Prayuth", "The minister", 3.59, "...", false, 15,"The great man ever!!!!", courseList, new User() ));
		stuList.add(new Student(3, "SE-003", "Jurgen", "Kloop", 2.15, "...", true, 2,"The man for the Kop", courseList, new User()));

		StudentServiceImpl test = mock(StudentServiceImpl.class);
		when(test.queryStudent("")).thenReturn(stuList);
		when(test.queryStudent("Mitsuha")).thenReturn(testquery);


		assertEquals(test.queryStudent(""),stuList);
		assertEquals(test.queryStudent("Mitsuha"),testquery);
		assertEquals(test.queryStudent("nonfromabove"),testBlank);
	}
}