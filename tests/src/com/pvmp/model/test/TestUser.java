package com.pvmp.model.test;

import junit.framework.TestCase;

import com.pvmp.models.User;

public class TestUser extends TestCase {
	
	private User user1;
	private User user2;

	protected void setUp() throws Exception {
		super.setUp();
		
		user1 = new User();
		user2 = new User("Jonathan", "john", "john123", "jonathan@gmail.com",
							23, "Superior", "Masculino", "S");
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
	
	public void testValidePasswordSizeInvalid() {
		assertFalse(User.validatePasswordSize("abcdefghijklmn123456"));
	}
	
	public void testValidatePasswordFormatValid() {
		assertTrue(User.validatePasswordFormat("john123"));
	}
	
	public void testValidatePasswordFormatInvalid() {
		assertFalse(User.validatePasswordFormat("Abc@123_hgJs=#lasd"));
	}
	
	public void testValidationResultNameFormat() {
		user1.setName("João123");
		//assertEquals(1, user1.validationResult(user1, context));
	}
}
