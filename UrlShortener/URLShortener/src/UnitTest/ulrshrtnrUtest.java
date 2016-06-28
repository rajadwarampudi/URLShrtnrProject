package UnitTest;

import java.math.BigInteger;
import org.junit.Assert;
import urlShortnerPack.urlshrtnr;

public class ulrshrtnrUtest {
	
	 BigInteger shrtLinkTest;
	 String orgLinkTest;
	 String shrtLinkBase36Test;
	
	public void AllVariableAssignmentCheck(){
		
		shrtLinkTest = new BigInteger("23456789");
		orgLinkTest = "www.gooooooooooooooooooogle.com";
		shrtLinkBase36Test = "shr.rr/";
		
		urlshrtnr usChk1 = new urlshrtnr(shrtLinkTest,orgLinkTest,shrtLinkBase36Test);
		
		Assert.assertEquals(orgLinkTest, usChk1.getOrgLink());
		Assert.assertEquals(shrtLinkTest, usChk1.getShrtLink());
		Assert.assertEquals(shrtLinkBase36Test, usChk1.getShrtLinkBase36());
	}

}
