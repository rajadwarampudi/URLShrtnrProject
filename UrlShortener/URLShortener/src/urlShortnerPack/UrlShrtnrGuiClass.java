package urlShortnerPack;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import org.apache.commons.validator.routines.UrlValidator;

public class UrlShrtnrGuiClass {

	private JFrame frame;
	private JTextField orgUrlTxtField;
	private JTextField shrtUrlTxtField;
	private JTextField ResultTxtField;
	
	private UrlShrtnrBean urlShbean = new UrlShrtnrBean();
    
	public static final String SHRT_URLDOMAIN     = "shr.rr/";    //shortened url domain
	public static final int    SHRT_URLDOMAIN_LEN = 7;            //shortened url domain length
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UrlShrtnrGuiClass window = new UrlShrtnrGuiClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UrlShrtnrGuiClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		orgUrlTxtField = new JTextField();
		orgUrlTxtField.setBounds(10, 28, 228, 20);
		frame.getContentPane().add(orgUrlTxtField);
		orgUrlTxtField.setColumns(10);
		
		shrtUrlTxtField = new JTextField();
		shrtUrlTxtField.setBounds(10, 87, 228, 20);
		frame.getContentPane().add(shrtUrlTxtField);
		shrtUrlTxtField.setColumns(10);
		
		JButton btnGetShortUrl = new JButton("Get Short URL");
		btnGetShortUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo) {
				String orgUrl;
				try{
					orgUrl = orgUrlTxtField.getText();
					//validate the original url
					
					/*the below validation not working as expected. need to check
					 * 
					 */
					String[] schemes = {"http","https","ftp"};
					
					UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_ALL_SCHEMES);
					
					if(!urlValidator.isValid(orgUrl))
					{
						throw new Exception();
					}
					
					
					
					urlshrtnr orgUrlInput = new urlshrtnr(orgUrl);
					
					//Here we need to call method that returns shortened url
					urlshrtnr UrlOutput = urlShbean.createShrtLink(orgUrlInput);
					
					ResultTxtField.setText(SHRT_URLDOMAIN+UrlOutput.getShrtLinkBase36());
					
				}catch(Exception eo){
					JOptionPane.showMessageDialog(null, "Please enter valid URL to shorten");
					
				}
			}
		});
		btnGetShortUrl.setBounds(263, 27, 106, 23);
		frame.getContentPane().add(btnGetShortUrl);
		
		JButton btnGetOrgUrl = new JButton("Get Org URL");
		btnGetOrgUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String shrturlLink   = null;
				String shrturlBase36 = null;
				String domainName    = null;
				BigInteger shrtLink;
			
				try{
					shrturlLink = shrtUrlTxtField.getText();
					//validate the short url
					domainName = shrturlLink.substring(0, Math.min(shrturlLink.length(), SHRT_URLDOMAIN_LEN));
					
					if(domainName.length() < 7 || !domainName.equals(SHRT_URLDOMAIN))
					{
						throw new Exception();
					}	
					else
					{
					   //domain name is valid. check short url unique code
						shrturlBase36 = shrturlLink.substring(7, shrturlLink.length());
					}
					//get the base 10 number for the given short url
					shrtLink = new BigInteger(shrturlBase36, 36);
					
					urlshrtnr shrtUrlInput = new urlshrtnr(shrtLink);
					
					//Here we need to call method that returns original url
					
					urlshrtnr UrlOutput = urlShbean.retrieveOrgLink(shrtUrlInput);
					
					if(!UrlOutput.getOrgLink().isEmpty())
					ResultTxtField.setText(UrlOutput.getOrgLink());
					
				}catch(Exception es){
					JOptionPane.showMessageDialog(null, "Please enter valid short URL to get original URL");
				}
				
			}
		});
		btnGetOrgUrl.setBounds(263, 86, 106, 23);
		frame.getContentPane().add(btnGetOrgUrl);
		
		ResultTxtField = new JTextField();
		ResultTxtField.setBounds(10, 161, 342, 20);
		frame.getContentPane().add(ResultTxtField);
		ResultTxtField.setColumns(10);
	}
}
