package com.pvmp.model.test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.pvmp.controller.UserController;
import com.pvmp.model.User;

public class TestUser extends AndroidTestCase {
	
	private RenamingDelegatingContext context;
	private User user1;
	private User user2;
	private UserController userC;

	protected void setUp() throws Exception {
		super.setUp();
		
		this.context = new RenamingDelegatingContext(getContext(), "test.");
		this.context.makeExistingFilesAndDbsAccessible();
		
		this.user1 = new User();
		this.user2 = new User("Jonathan", "john", "john123", "jonathan@gmail.com",
							23, "Superior", "Masculino", "S");
		this.userC = new UserController(this.context);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetName() {
		assertEquals(user2.getName(), "Jonathan");
	}
	public void testSetName() {
		user1.setName("Amanda");
		assertEquals(user1.getName(), "Amanda");
	}

	public void testGetUsername() {
		assertEquals(user2.getUsername(), "john");
	}
	public void testSetUsername() {
		user1.setUsername("amandinha");
		assertEquals(user1.getUsername(), "amandinha");
	}

	public void testGetPassword() {
		assertEquals(user2.getPassword(), "john123");
	}
	public void testSetPassword() {
		user1.setPassword("amanda123");
		assertEquals(user1.getPassword(), "amanda123");
	}

	public void testGetEmail() {
		assertEquals(user2.getEmail(), "jonathan@gmail.com");
	}
	public void testSetEmail() {
		user1.setEmail("amanda@gmail.com");
		assertEquals(user1.getEmail(), "amanda@gmail.com");
	}

	public void testGetAge() {
		assertEquals(user2.getAge(), 23);
	}
	public void testSetAge() {
		user1.setAge(24);
		assertEquals(user1.getAge(), 24);
	}

	public void testGetEducation() {
		assertEquals(user2.getEducation(), "Superior");
	}
	public void testSetEducation() {
		user1.setEducation("Superior");
		assertEquals(user1.getEducation(), "Superior");
	}
	
	public void testGetSex() {
		assertEquals(user2.getSex(), "Masculino");
	}
	public void testSetSex() {
		user1.setSex("Feminino");
		assertEquals(user1.getSex(), "Feminino");
	}
	
	public void testGetDefaultUser () {
		assertEquals("S", user2.getDefaultUser());
	}
	public void testSetDefaultUser () {
		user1.setDefaultUser("N");
		assertEquals("N", user1.getDefaultUser());
	}

	public void testValidateNameFormat() {
		assertEquals(User.validateNameFormat("Lucas V Mattioli"), true);
		assertEquals(User.validateNameFormat("Jonathan"), true);
		assertEquals(User.validateNameFormat("Jonathan*"), false);
		assertEquals(User.validateNameFormat("    "), false);
		assertEquals(User.validateNameFormat("Joao123"), false);
		assertEquals(User.validateNameFormat("Jo&ao"), false);
		assertEquals(User.validateNameFormat("João"), true);
		assertEquals(User.validateNameFormat("José"), true);
		assertEquals(User.validateNameFormat("Úrsula"), true);
		assertEquals(User.validateNameFormat("Maitê"), true);
		assertEquals(User.validateNameFormat("Átila Gonçalves"), true);
	}

	public void testValidateNameSize() {
		assertEquals(User.validateNameSize(""), false);
		assertEquals(User.validateNameSize("a"), false);
		assertEquals(User.validateNameSize("ab"), false);
		assertEquals(User.validateNameSize("abc"), true);
		
		String message = new String ();
		for (int i = 0; i < 50; i++) {
			message += 'a';
		}
		assertEquals(User.validateNameSize(message), true);
		
		message +='a';
		assertEquals(User.validateNameSize(message), false);
	}

	public void testValidateEmailFormat() {
		assertEquals(User.validateEmailFormat("jbs@email.com"),true);
		assertEquals(User.validateEmailFormat("asdd#kkss"),false);
		assertEquals(User.validateEmailFormat(" "),false);
		assertEquals(User.validateEmailFormat("{}sss@email.com"),false);
		assertEquals(User.validateEmailFormat("12krk@email.com"),true);
	}

	public void testValidateEmailSize() {
		assertEquals(User.validateEmailSize("fjfk@email.com"),true);
		assertEquals(User.validateEmailSize("cmfjfmfj1234567890cmdkvmsovkfv@email.com"),false);
	}

	public void testValidateAge() {
		assertEquals(User.validateAge(9), false);
		assertEquals(User.validateAge(100), false);
		assertEquals(User.validateAge(10), true);
		assertEquals(User.validateAge(-10), false);
		assertEquals(User.validateAge(99), true);
		assertEquals(User.validateAge(10000), false);
	}

	public void testValidateUsernameSize() {
		assertEquals(User.validateUsernameSize("aaa"),true);
		assertEquals(User.validateUsernameSize("a1"),false);
		assertEquals(User.validateUsernameSize("123456789abcdef"),true);
		assertEquals(User.validateUsernameSize("abcdefg123456789"),false);
		assertEquals(User.validateUsernameSize("aaa12"),true);
	}

	public void testValidateUsernameFirstLetter() {
		assertEquals(User.validateUsernameFirstLetter("a123"),true);
		assertEquals(User.validateUsernameFirstLetter("2a1a"),false);
	}

	public void testValidateUsernameFormat() {
		assertEquals(User.validateUsernameFormat("Ajdj22"),true);
		assertEquals(User.validateUsernameFormat("$aja12"),false);
		assertEquals(User.validateUsernameFormat("0*"),false);
		assertEquals(User.validateUsernameFormat("aoo1234d"),true);
	}
	
	public void testValidatePasswordSizeValid() {
		assertTrue(User.validatePasswordSize("john123"));
	}
	
	public void testValidatePasswordSizeInvalid() {
		assertFalse(User.validatePasswordSize("abcdefghijklmn123456"));
	}
	
	public void testValidatePasswordFormatValid() {
		assertTrue(User.validatePasswordFormat("john123"));
	}
	
	public void testValidatePasswordFormatInvalid() {
		assertFalse(User.validatePasswordFormat("Abc@123_hgJs=#lasd"));
	}
	
	public void testValidationResultInvalidNameFormat() {
		user2.setName("João123");
		assertEquals(1, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidNameSize () {
		user2.setName("Ju");
		assertEquals(2, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidEmailFormat () {
		user2.setEmail("joao.hotmail");
		assertEquals(3, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidEmailSize () {
		String email = "oi123455123423@hotadhashdhaahuhudadasbasdsahuddmail.com";
		user2.setEmail(email);
		assertEquals(4, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidAge () {
		user2.setAge(100);
		assertEquals(5, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidPasswordSize () {
		user2.setPassword("123456123456adadsadad");
		assertEquals(6, user2.validationResult(user2, this.context));
	}
	public void testValidationResultExistingEmail () {
		this.user1 = new User("Jonathan", "johnSILvestre", "john123", "jonathan@gmail.com",
				23, "Superior", "Masculino", "S");
		this.userC.removeUser(user2);
		this.userC.saveUser(user2);
		assertEquals(7, user1.validationResult(user1, this.context));
		this.userC.removeUser(user2);
	}
	public void testValidationResultExistingUser () {
		this.user1 = new User("Jonathan", "john", "john123", "jonathan123@gmail.com",
				23, "Superior", "Masculino", "S");
		this.userC.removeUser(user2);
		this.userC.saveUser(user2);
		assertEquals(8, user1.validationResult(user1, this.context));
		this.userC.removeUser(user2);
	}
	public void testValidationResultInvalidUsernameSize () {
		user2.setUsername("ab");
		user2.setEmail("test9@test9.com");
		assertEquals(9, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidUsernameFirstLetter () {
		user2.setUsername("1joaobala");
		user2.setEmail("test10@test10.com");
		assertEquals(10, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidUsernameFormat () {
		user2.setUsername("Juca-baleta**");
		user2.setEmail("test11@test11.com");
		assertEquals(11, user2.validationResult(user2, this.context));
	}
	public void testValidationResultInvalidPasswordFormat () {
		user2.setPassword("juks**");
		user2.setEmail("test12@test12.com");
		user2.setUsername("Username12");
		assertEquals(12, user2.validationResult(user2, this.context));
	}
	public void testValidationResultOK () {
		this.user2 = new User("Joao", "jujuba", "senha1234", "joao@email.com", 19, "Superior", "M", "N");
		assertEquals(0, user2.validationResult(user2, this.context));
	}
	
}
