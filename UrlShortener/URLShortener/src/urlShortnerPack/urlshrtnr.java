import java.io.Serializable;
import java.math.BigInteger;

/**
 * 
 */

/**
 * @author Raja dwarampudi
 *
 */
public class urlshrtnr implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This variables represent urlshrtnr table structure with url_id and url_link info
	 */
	private BigInteger shrtLink;
	private String orgLink;
	private String shrtLinkBase36;
	
	public BigInteger getShrtLink() {
		return shrtLink;
	}

	
	public void setShrtLink(BigInteger shrtLink) {
		this.shrtLink = shrtLink;
	}

	
	public String getShrtLinkBase36() {
		return shrtLinkBase36;
	}
	
	public void setShrtLinkBase36(String shrtLinkBase36) {
		this.shrtLinkBase36 = shrtLinkBase36;
	}
	
	public String getOrgLink() {
		return orgLink;
	}
	
	public void setOrgLink(String orgLink) {
		this.orgLink = orgLink;
	}

	//Constructor for the record received from database
	public urlshrtnr(BigInteger shrtLink, String orgLink,String shrtLinkBase36) {
		super();
		this.shrtLink       = shrtLink;
		this.orgLink        = orgLink;
		this.shrtLinkBase36 = shrtLinkBase36;
	}
		
	//Constructor for the record received from database
	public urlshrtnr(BigInteger shrtLink, String orgLink) {
		super();
		this.shrtLink = shrtLink;
		this.orgLink = orgLink;
	}
	
	//Constructor for the record to be inserted into the table
	public urlshrtnr( String orgLink) {
		super();
		this.orgLink = orgLink;
	}
	
	public urlshrtnr() {
		super();
	}
	
	public urlshrtnr(BigInteger shrtLink) {
		super();
		this.shrtLink = shrtLink;
	}
	
	public urlshrtnr(urlshrtnr aUrl) {
		
	    this(aUrl.getShrtLink(),aUrl.getOrgLink(),aUrl.getShrtLinkBase36());

	}


}
