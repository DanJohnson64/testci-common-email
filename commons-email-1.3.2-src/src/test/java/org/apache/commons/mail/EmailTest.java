package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	
	//Test data 
	private static final String[] TEST_EMAILS = {"ab@bc.com", 
	"a.b.c@g.org", "asdfasdadsads@asdasdasd.com.bc"};	
	
	private String[] testValidChars = {" ", "a", "A", "\uc5ec", "0123456789", "75468932075483920", "/n"};
	
	
	
	/* Concrete email before testing */
	private EmailConcrete email;
	
	@Before
	public void setUpEmailTest() throws Exception{
		
		email = new EmailConcrete();
	}
	
	@After
	public void tearDownEmailTest() throws Exception{
		
		
		
	}
	
	//test addBcc with good data
	@Test
	public void testAddBcc() throws Exception{

		
		email.addBcc(TEST_EMAILS);
		
		assertEquals(3, this.email.getBccAddresses().size());
	}
	
	//test addBcc with null data
	@Test
	public void testAddBccException() throws Exception{
		try {
			String emails[] = null;		
			email.addBcc(emails);
			fail("Expected EmailException to be thrown");
		} catch(EmailException e) {
			assertEquals("Address List provided was invalid", e.getMessage());
		}		
		
	}
	
	@Test
	public void testAddCc() throws Exception{

		
		email.addCc("test@test.com");
		
		assertEquals(1, this.email.getCcAddresses().size());
	}
	
	//test addBcc with null data
	@Test 
	public void addHeaderTest() throws Exception{
		email.addHeader("test name", "test value");
		
		assertEquals("test value", email.headers.get("test name"));
	}
	
	@Test 
	public void addHeaderTestNoName() throws Exception{
		try {
			email.addHeader("", "test value");
			fail("expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e){
			assertEquals("name can not be null or empty", e.getMessage());
		}
		
	}
	
	@Test 
	public void addHeaderTestNoValue() throws Exception{
		try {
			email.addHeader("test name", "");
			fail("expected IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e){
			assertEquals("value can not be null or empty", e.getMessage());
		}
	}
	
	@Test 
	public void addReplyToTest() throws Exception{
		email.addReplyTo("sender@sender.com", "David Test");
		List<InternetAddress> replyList = email.getReplyToAddresses();
		assertEquals("sender@sender.com", replyList.get(0).getAddress());
		assertEquals("David Test", replyList.get(0).getPersonal());
		}
	
	@Test
	public void testBuildMimeMessage_MessageAlreadyBuilt() throws Exception{
		email.buildMimeMessage();
		assertNotNull("MimeMesage should not be null", email.getMimeMessage());
	}
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	

