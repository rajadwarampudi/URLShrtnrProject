package UnitTest;

import java.math.BigInteger;
import org.junit.Assert;

import urlShortnerPack.UrlShrtnrBean;
import urlShortnerPack.urlshrtnr;

public class JdbcBeanTest {
	
	BigInteger shrtLinkTest;
	String orgLinkTest;
	String shrtLinkBase36Test;
	public void InsertRetrieveCheck(){
	
		shrtLinkTest = new BigInteger("23456789");
		orgLinkTest = "www.gooooooooooooooooooogle.com";
		shrtLinkBase36Test = "shr.rr/"; 
		
		urlshrtnr usChk1 = new urlshrtnr(shrtLinkTest,orgLinkTest,shrtLinkBase36Test);
		
		UrlShrtnrBean usChkBean = new UrlShrtnrBean();
		
		urlshrtnr usInsertdRec =usChkBean.createShrtLink(usChk1);
		Assert.assertNotNull(usInsertdRec.getShrtLinkBase36());
		Assert.assertEquals(orgLinkTest, usInsertdRec.getOrgLink());
		
		try {
			urlshrtnr usRetrievedRec = usChkBean.retrieveOrgLink(usInsertdRec);
			
			Assert.assertNotNull(usRetrievedRec.getShrtLinkBase36());
			Assert.assertEquals(orgLinkTest, usRetrievedRec.getOrgLink());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
