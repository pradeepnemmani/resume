package target;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import target.Resume.PersonalDetails;
import target.Resume.Projects;
import target.Resume.Projects.Project;
import target.Resume.TechnicalSkills;


public class TestResume {
	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		ObjectFactory of = new ObjectFactory();
		target.Resume resume = of.createResume();

		resume.setCareerObjective("I will work very enthuciastic and very I will prove my self");

		Resume.Qualifications qualifications = of.createResumeQualifications();
		Resume.Qualifications.Qualification btechqualification = of
				.createResumeQualificationsQualification();
		btechqualification.setExamination("B.tech(CSE)");
		btechqualification.setCollege("JNIT");
		btechqualification.setBoardSchool("JNTUH");
		btechqualification.setYear("2013");
		btechqualification.setPercentage("74");

		Resume.Qualifications.Qualification interQualification = of
				.createResumeQualificationsQualification();
		interQualification.setExamination("Inter(MPC)");
		interQualification.setCollege("Sri Aurobindo Jr College");
		interQualification.setBoardSchool("IPE");
		interQualification.setYear("2009");
		interQualification.setPercentage("80");

		Resume.Qualifications.Qualification sscQualification = of
				.createResumeQualificationsQualification();
		sscQualification.setExamination("10th");
		sscQualification.setCollege("The Golden High School");
		sscQualification.setBoardSchool("SSC");
		sscQualification.setYear("2007");
		sscQualification.setPercentage("77");

		qualifications.getQualification().add(btechqualification);
		qualifications.getQualification().add(interQualification);
		qualifications.getQualification().add(sscQualification);

		resume.setQualifications(qualifications);

		TechnicalSkills skills = of.createResumeTechnicalSkills();
		skills.setProgrammingLanguage("Java,Servlets,Jsp,Swings");
		skills.setDatabase("Neo4j,RDBMS");
		skills.setOperatingSystem("Linux,Windows");
		skills.setOthers("Eclips,intelIJ idea, Workbench,PhotoShop");
		resume.setTechnicalSkills(skills);

		Projects projects = of.createResumeProjects();
		Project major_project = of.createResumeProjectsProject();
		major_project.setProjectType("Mini_Project");
        try

        {
            DatatypeFactory dtf=DatatypeFactory.newInstance();
            GregorianCalendar gc=new GregorianCalendar();
        XMLGregorianCalendar gcc=dtf.newXMLGregorianCalendarDate(Calendar.YEAR,Calendar.MONTH,Calendar.HOUR_OF_DAY,0);
            major_project.setProjectStart(gcc);
            major_project.setProjectEnd(gcc);
        }
        catch(DatatypeConfigurationException e)
        {
            e.printStackTrace();
        }


		major_project
				.setProjectTitle("Online Crime Investigation Using Three tyre Architecture");
		major_project
				.setDescription("This helps to file the case through online ,through this service client can get to know the case status");

		Project mini_project = of.createResumeProjectsProject();
		mini_project.setProjectType("Mini_Project");
        try
        {
            DatatypeFactory dtf=DatatypeFactory.newInstance();
            GregorianCalendar gc=new GregorianCalendar();
            XMLGregorianCalendar gcc=dtf.newXMLGregorianCalendarDate(Calendar.YEAR,Calendar.MONTH,Calendar.HOUR_OF_DAY,0);
            mini_project.setProjectStart(gcc);
            mini_project.setProjectEnd(gcc);
        }
        catch(DatatypeConfigurationException e)
        {
            e.printStackTrace();
        }

		mini_project
				.setProjectTitle("Online Crime Investigation Using Three tyre Architecture");
		mini_project
				.setDescription("This helps to file the case through online ,through thes service client can get to know thw case status");
		projects.getProject().add(mini_project);
		projects.getProject().add(major_project);
		resume.setProjects(projects);
		resume.setAcchievements("Awarded From NATIONAL ART and SERVICE for Drawing In school drawing competion"
				+ "\n"

				+ "Sucessfully complered HIGH VALUE SKILL DEVELOPMENT PROGRAM IN dots2drops ");
		resume.setSeminarsAttended("Hadoop-4 Day HAnds-On  Workshop" + "\n"
				+ "Art Of Learning" + "\n" + "Problem Solving Skills" + "\n"
				+ "Working in team");
		PersonalDetails personalDetails = of.createResumePersonalDetails();
		personalDetails.setName("Nemmani Pradeep Kumar");
		personalDetails.setFatherName("Nemmani Kalinga Chary");
		personalDetails.setGender("male");
		personalDetails.setDob("01-06-1991");
		personalDetails.setMaritalStatus(EnumMaritalStatus.Unmarried);
		personalDetails.setNationality("Indian");
		personalDetails.setSpokenLanguages("English,Hindi,Telugu");
		ContactDetails contactDetails = of.createContactDetails();
		contactDetails.setStreet("H.No:-8-1/c-684");
		contactDetails.setLandmark("besided YellaReddy Colony Hall");
		contactDetails.setArea("Hayathnagar");
		contactDetails.setCity("Hyderabad");
		contactDetails.setState("Andhra Pradesh");
		contactDetails.setCountry("India");
		contactDetails.setZipcode("500082");
		contactDetails.setPhoneNo(90);
		contactDetails.setEmailId("truepradeep91@gmail.com");

		personalDetails.setAddress(contactDetails);

		target.Address address = of.createAddress();
		address.setStreet("H.No:-5-11-284");
		address.setLandmark("Opposite Little Flower Jr College");
		address.setArea("Shanti Nagar");
		address.setCity("Nalgonda");
		address.setState("Andhra Pradesh");
		address.setCountry("India");
		address.setZipcode("500008");
		personalDetails.setPermenentAddress(address);

		resume.setPersonalDetails(personalDetails);

		try {
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(
					"C:\\EMAIL_PROJECT\\xml_project\\src\\resume\\resume.xsd"));
			JAXBContext jax = JAXBContext.newInstance("target");
			Marshaller marshell = jax.createMarshaller();
			marshell.setSchema(schema);
			marshell.setProperty(marshell.JAXB_FORMATTED_OUTPUT, true);
			marshell.marshal(resume, System.out);
            marshell.marshal(resume,new File("C:\\d2d\\output.xsd"));
		} catch (JAXBException jax) {
			jax.printStackTrace();
		} catch (SAXException sax) {
			sax.printStackTrace();
			// TODO: handle exception
		}

	}
}
