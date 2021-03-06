package sut.se.team15.backend;

import sut.se.team15.Repository.ProvinceRepository;
import sut.se.team15.Repository.StatusRepository;
import sut.se.team15.Repository.TitleRepository;
import sut.se.team15.Repository.UserRepository;
import sut.se.team15.Entity.Province;
import sut.se.team15.Entity.Title;
import sut.se.team15.Entity.User;
import sut.se.team15.Entity.Status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class UserTests {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);

    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private StatusRepository statusRepository;
    
    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6005924_testUserIdOKWith13Digits() { // ใส่ข้อมูลปกติ

        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("12345678");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        user = userRepository.saveAndFlush(user);

        Optional<User> found = userRepository.findById(user.getId());
        assertEquals("1234567890123", found.get().getUserid());
        assertEquals(BirthDay, found.get().getBirthDay());
    }

    @Test
    void B6005924_testUserIdMustNotBeNull() {
        User user = new User();
        user.setUserid(null);
        user.setPassword("12345678");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("userid", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testUserIdMustNotBe14Digits() {
        User user = new User();
        user.setUserid("12345678901234");
        user.setPassword("12345678");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("userid", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testUserIdMustNotBe12Digits() {
        User user = new User();
        user.setUserid("123456789012");
        user.setPassword("12345678");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must match \"\\d{13}\"", v.getMessage());
        assertEquals("userid", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPasswordMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword(null);
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPasswordMustNotBeSizeThen17() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("PasswordPasswordPassword");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);

        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 8 and 16", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPasswordMustNotBeSizeThen7() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Passwor");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);

        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 8 and 16", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testFirstNameMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName(null);
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("FirstName", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testFirstNameMustNotBeSizeThen201() {
        User user = new User();

        String FirstName = "" ;
        int i = 0;
        while(i<201){
            FirstName += "a";
            i++;
        }

        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName(FirstName);
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        assertEquals("FirstName", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testFirstNameMustNotBeSizeThen1() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("a");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        assertEquals("FirstName", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testLastNameMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName(null);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("LastName", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testLastNameMustNotBeSizeThen201() {
        User user = new User();

        String LastName = "" ;
        int i = 0;
        while(i<201){
            LastName += "a";
            i++;
        }

        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName(LastName);
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        assertEquals("LastName", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testLastNameMustNotBeSizeThen1() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("L");
        user.setBirthDay(BirthDay );
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 2 and 200", v.getMessage());
        assertEquals("LastName", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testBirthDayMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(null);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("BirthDay", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testBirthDayWrongFormatFutureOrPresent() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2022-01-25", formatter);
        user.setBirthDay(BirthDay);

        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must be a past date", v.getMessage());
        assertEquals("BirthDay", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testAgeMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge(null);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Age", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testAgeMustBeLessThan101() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)105);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must be less than or equal to 100", v.getMessage());
        assertEquals("Age", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testAgeMustBeMoreThan4() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)4);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must be greater than or equal to 5", v.getMessage());
        assertEquals("Age", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testCareerMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer(null);
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Career", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testCareerMustNotBeSizeThen201() {
        User user = new User();

        String Career = "" ;
        int i = 0;
        while(i<201){
            Career += "a";
            i++;
        }

        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate BirthDay = LocalDate.parse("2020-01-10", formatter);
        user.setBirthDay(BirthDay);

        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer(Career);
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("Career", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testCareerMustNotBeSizeThen4() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Care");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("Career", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testDiseaseMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease(null);
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Disease", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testDiseaseMustNotBeSizeThen201() {
        User user = new User();

        String Disease = "" ;
        int i = 0;
        while(i<201){
            Disease += "a";
            i++;
        }

        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease(Disease);
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("Disease", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testDiseaseMustNotBeSizeThen4() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Dise");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 200", v.getMessage());
        assertEquals("Disease", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPhoneNumberMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber(null);
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("PhoneNumber", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPhoneNumberMustNotBeSizeThen11() {
        User user = new User();

        String PhoneNumber = "" ;
        int i = 0;
        while(i<11){
            PhoneNumber += "a";
            i++;
        }

        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber(PhoneNumber);
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 10", v.getMessage());
        assertEquals("PhoneNumber", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPhoneNumberMustNotBeSizeThen4() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 10", v.getMessage());
        assertEquals("PhoneNumber", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testAddressMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress(null);
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Address", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testAddressMustNotBeSizeThen251() {
        User user = new User();

        String Address = "" ;
        int i = 0;
        while(i<251){
            Address += "a";
            i++;
        }

        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress(Address);
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 250", v.getMessage());
        assertEquals("Address", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testAddressMustNotBeSizeThen4() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Addr");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)30000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("size must be between 5 and 250", v.getMessage());
        assertEquals("Address", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPostalCodeMustNotBeNull() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode(null);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("PostalCode", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testPostalCodeMustBeLessThen100000() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)100000);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must be less than or equal to 99999", v.getMessage());
        assertEquals("PostalCode", v.getPropertyPath().toString());
    }

    @Test
    void B6005924_testAgeMustBeMoreThan9999() {
        User user = new User();
        user.setUserid("1234567890123");
        user.setPassword("Password");
        Title title  = titleRepository.findById(1);
        user.setTitle(title);
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setBirthDay(BirthDay);
        user.setAge((long)20);
        Status status = statusRepository.findById(1);
        user.setStatus(status);
        user.setCareer("Career");
        user.setDisease("Disease");
        user.setPhoneNumber("0123456789");
        user.setAddress("Address");
        Province province = provinceRepository.findById(1);
        user.setProvince(province);
        user.setPostalCode((long)9999);

        Set<ConstraintViolation<User>> result = validator.validate(user);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<User> v = result.iterator().next();
        assertEquals("must be greater than or equal to 10000", v.getMessage());
        assertEquals("PostalCode", v.getPropertyPath().toString());
    }
}  