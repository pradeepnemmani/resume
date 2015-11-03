import org.xml.sax.SAXException;
import target.*;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

/**
 * Created by re on 4/17/2014.
 */
public class TestResume2 {


    private static Schema schema = null;


    private static  final String xsdPath = "C:\\EMAIL_PROJECT\\xml_project\\src\\resume\\resume.xsd";


    private static Schema getTrainingSchema( String fileName )
    {
        if ( schema == null )
        {
            SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
            try
            {
                schema = sf.newSchema( new File( fileName ) );
            }
            catch ( SAXException e )
            {
                e.printStackTrace();
            }
        }
        return schema;
    }
    private  static void serialize(JAXBContext jax,Object resume,String file)
    {
        try {
            Marshaller marshaller = jax.createMarshaller();
            marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT,true);
            Schema schema=TestResume2.getTrainingSchema(TestResume2.xsdPath);
            marshaller.setSchema(schema);

            marshaller.marshal(resume,new File(file));

        }catch(JAXBException e)
        {
            e.printStackTrace();
        }
    }
    private static Resume deSerialize(JAXBContext jax,String xmlFile)
    {
        Resume resume=null;
        try {
        Unmarshaller unMarshaller = jax.createUnmarshaller();
             Schema schema=TestResume2.getTrainingSchema(TestResume2.xsdPath);
            unMarshaller.setSchema(schema);
           Object object= unMarshaller.unmarshal(new File(xmlFile));
            if(object instanceof Resume)
            {
                 resume=(Resume) object;
            }
    }
    catch (JAXBException e)
    {
        e.printStackTrace();
    }
        return resume;
    }


private static Resume createResume(ObjectFactory of)
{
     Resume resume =of.createResume();
    resume.setCareerObjective("I will work very enthuciastic and very I will prove my self, and I hope I can work easily");

    Resume.Qualifications qualifications= of.createResumeQualifications();

    Resume.Qualifications.Qualification btechQualification=of.createResumeQualificationsQualification();
    btechQualification=TestResume2.createQualification(of,"B.Tech(CSE)","JNIT","JNTU H","2013","74");
    Resume.Qualifications.Qualification interQualification=of.createResumeQualificationsQualification();
    interQualification=TestResume2.createQualification(of,"Inter(MPC)","Sri Aurobindo Jr college","IPE","2009","80");
    Resume.Qualifications.Qualification sscQualification=of.createResumeQualificationsQualification();
    sscQualification=TestResume2.createQualification( of,"10th","The Golden High School","SSC","2007","77");

    qualifications.getQualification().add(btechQualification);
    qualifications.getQualification().add(interQualification);
    qualifications.getQualification().add(sscQualification);

    resume.setQualifications(qualifications);


    Resume.TechnicalSkills  techSkills=of.createResumeTechnicalSkills();
    techSkills=TestResume2.createSkills(of,"Java,Servlet,Jsp,Swing","Neo4j,RDBMS","Linux,Windows","IntelIj,Eclips,PhotoShop");

    resume.setTechnicalSkills(techSkills);

     Resume.Projects projects=of.createResumeProjects();


     Resume.Projects.Project mini_project=of.createResumeProjectsProject();
    Resume.Projects.Project major_project=of.createResumeProjectsProject();
    XMLGregorianCalendar miniProjectStartDate=TestResume2.createcalander(of,2012,04,05);
    XMLGregorianCalendar miniProjectEndDate=TestResume2.createcalander(of,2012,06,05);
    mini_project=TestResume2.createProject(of,"mini_project",miniProjectStartDate,miniProjectEndDate,"Online Crime Investigation ","This helps to file the case through online ,through this service client can get to know the case status");

    XMLGregorianCalendar majorProjectStartDate=TestResume2.createcalander(of,2013,01,05);
    XMLGregorianCalendar majorProjectEndDate=TestResume2.createcalander(of,2013,03,05);
major_project=TestResume2.createProject(of,"Major_Project",majorProjectStartDate,majorProjectEndDate,"Online Crime Investigation","This helps to file the case through online ,through this service client can get to know the case status");

    projects.getProject().add(mini_project);
    projects.getProject().add(major_project);
    resume.setProjects(projects);

    resume.setAcchievements("Awarded From NATIONAL ART and SERVICE for Drawing In school drawing competion"
            + "\n"

            + "Sucessfully complered HIGH VALUE SKILL DEVELOPMENT PROGRAM IN dots2drops ");
    resume.setSeminarsAttended("Hadoop-4 Day HAnds-On  Workshop" + "\n"
            + "Art Of Learning" + "\n" + "Problem Solving Skills" + "\n"
            + "Working in team");

    Resume.PersonalDetails personalDetails= of.createResumePersonalDetails();

     Address address=of.createAddress();
    Address perminentAddress=of.createAddress();
    address=TestResume2.createAddress(of,"H,No:-4-5c","behind Yellareddy Comunity hall","HayathNagar","Hyderabad","Andhra Pradesh","India","500082");
    perminentAddress=TestResume2.createAddress(of,"H,No:5-11-284","opposite Little Flower Jr College","Shanti Nagar","Nalgonda","Andhra Pradesh","India","500008");

 ContactDetails contactDe=of.createContactDetails();

   contactDe =TestResume2.conDetails(of,address,90,"truepradeep91@gmail.com");


    personalDetails=TestResume2.createPersonalDetails(of, "pradeep.N", "Kalinga Chary", "male", "01-06-1991", EnumMaritalStatus.Unmarried, "Indian", "English,Hindi,Telugu", contactDe, address);
    resume
            .setPersonalDetails(personalDetails);
    return resume;
}
    private  static Resume.Qualifications.Qualification createQualification(ObjectFactory of ,String examination,String school_college,String board,String year,String percentage)

    {  Resume.Qualifications.Qualification qualification=of.createResumeQualificationsQualification();
        qualification.setExamination(examination);
        qualification.setCollege(school_college);
        qualification.setBoardSchool(board);
        qualification.setYear(year);
        qualification.setPercentage(percentage);
        return qualification;
    }

    private static Resume.TechnicalSkills createSkills(ObjectFactory of,String progamimg_lan,String databases,String operatingSys,String others)
    {
         Resume.TechnicalSkills skills=of.createResumeTechnicalSkills();
        skills.setProgrammingLanguage(progamimg_lan);
        skills.setDatabase(databases);
        skills.setOperatingSystem(operatingSys);
        skills.setOthers(others);
        return skills;
    }
    private  static Resume.Projects.Project createProject(ObjectFactory of,String type,XMLGregorianCalendar startDate,XMLGregorianCalendar endDate,String title,String description)
    {
       Resume.Projects.Project project=of.createResumeProjectsProject();
        project.setProjectType(type);
        project.setProjectStart(startDate);
        project.setProjectEnd(endDate);
        project.setProjectTitle(title);
        project.setDescription(description);

        return project;
    }
    private static Resume.PersonalDetails createPersonalDetails(ObjectFactory of,String name,String fatherNAme,String gender,String dob, EnumMaritalStatus maritalStatus,String nationality,String spokenLang , ContactDetails cDetais, Address address)
    {
      Resume.PersonalDetails  personalDetails=of.createResumePersonalDetails();
        personalDetails.setName(name);
        personalDetails.setFatherName(fatherNAme);
        personalDetails.setGender(gender);
        personalDetails.setDob(dob);
        personalDetails.setMaritalStatus(maritalStatus);

        personalDetails.setNationality(nationality);
        personalDetails.setSpokenLanguages(spokenLang);
        personalDetails.setAddress(cDetais);
        personalDetails.setPermenentAddress(address);


        return personalDetails;
    }
    private static ContactDetails conDetails(ObjectFactory of ,Address address,int phoneNo,String emailId)
    {
        ContactDetails  contDetails=of.createContactDetails();
        contDetails.setStreet(address.getStreet());
        contDetails.setLandmark(address.getLandmark());
        contDetails.setArea(address.getArea());
        contDetails.setCity(address.getCity());
        contDetails.setState(address.getState());
        contDetails.setCountry(address.getCountry());
        contDetails.setZipcode(address.getZipcode());
        contDetails.setPhoneNo(phoneNo);
        contDetails.setEmailId(emailId);
        return contDetails;
    }
    private static Address  createAddress(ObjectFactory of,String street,String landMark,String area,String city,String state,String country,String zipCode)
    {
         Address address=of.createAddress();
        address.setStreet(street);
        address.setLandmark(landMark);
        address.setArea(area);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZipcode(zipCode);
        return address;
    }
    private static XMLGregorianCalendar createcalander( ObjectFactory of, int year,int month,int day)
    {
        XMLGregorianCalendar xgs=null;
        try
        {
            DatatypeFactory date=DatatypeFactory.newInstance();
         xgs   =date.newXMLGregorianCalendarDate(year,month,day , DatatypeConstants.FIELD_UNDEFINED);        }
        catch(DatatypeConfigurationException e)
        {
            e.printStackTrace();
        }
        return xgs;
    }
    public static void printDetails(Resume resume)
    {
        System.out.println("Printing the details");
        System.out.println("CareerObjective");
        System.out.println("career_obj=="+resume.getCareerObjective());
        System.out.println("------------------------------------------");
       Resume.Qualifications qua=resume.getQualifications();
       List<Resume.Qualifications.Qualification> qualifications= qua.getQualification();
          System.out.println("Printing the Qualifications");
        for(Resume.Qualifications.Qualification qualification:qualifications)
        {
            System.out.println("examination==="+qualification.getExamination());
            System.out.println("school_colleg"+qualification.getCollege());
            System.out.println("board=="+qualification.getBoardSchool());
            System.out.println("year of pass"+qualification.getYear());
            System.out.println("percentage"+qualification.getPercentage());
            System.out.println("******************************************************");
        }
        System.out.println("-------------------------------------------------------");
        Resume.TechnicalSkills skills=resume.getTechnicalSkills();
        System.out.println("Printing the TechnicalSkills");
        System.out.println("programminng skills=="+skills.getProgrammingLanguage());
        System.out.println("database=="+skills.getDatabase());
        System.out.println("os===="+skills.getOperatingSystem());
        System.out.println("other skills=="+skills.getOthers());
        System.out.println("-------------------------------------------------------");
         Resume.Projects pro=resume.getProjects();
          List<Resume.Projects.Project>projects=pro.getProject();
        System.out.println("Printing the Projects ");
        for(Resume.Projects.Project project:projects)
        {
            System.out.println("project type=="+project.getProjectType());
            System.out.println("project start date=="+project.getProjectStart());
            System.out.println("project end date=="+project.getProjectEnd());
            System.out.println("project title== "+project.getProjectTitle());
            System.out.println("project desc"+project.getDescription());
            System.out.print("*************************");

        }
        System.out.println("-------------------------------------------------------");
        System.out.println("Printing the Acchievements==="+resume.getAcchievements());
        System.out.println("-------------------------------------------------------");
        System.out.println("seminars Attended==="+resume.getSeminarsAttended());
        System.out.println("-------------------------------------------------------");
       Resume.PersonalDetails  personalDetails=resume.getPersonalDetails();
        System.out.println("Printing the PersonalDetails");

        System.out.println("name=="+personalDetails.getName());
        System.out.println("father name=="+personalDetails.getFatherName());
        System.out.println("Dob=="+personalDetails.getDob());
        System.out.println("Gender=="+personalDetails.getGender());
        System.out.println("Nationality=="+personalDetails.getNationality());
        System.out.println("Spoken Lang=="+personalDetails.getSpokenLanguages());
        System.out.println("Printing the Address");
       ContactDetails cDetails=personalDetails.getAddress();
        System.out.println("street==="+cDetails.getStreet());
        System.out.println("landMark==="+cDetails.getLandmark());
        System.out.println("area==="+cDetails.getArea());
        System.out.println("city==="+cDetails.getCity());
        System.out.println("state==="+cDetails.getState());
        System.out.println("country==="+cDetails.getCountry());
        System.out.println("zipCode==="+cDetails.getZipcode());
       System.out.println("phone no"+ cDetails.getPhoneNo());
        System.out.println("EmailId"+ cDetails.getEmailId());
        System.out.println("Printing the perManent address");
       Address address=personalDetails.getPermenentAddress();
        System.out.println("street==="+address.getStreet());
        System.out.println("landMark==="+address.getLandmark());
        System.out.println("area==="+address.getArea());
        System.out.println("city==="+address.getCity());
        System.out.println("state==="+address.getState());
        System.out.println("country==="+address.getCountry());
        System.out.println("zipCode==="+address.getZipcode());




    }
public static void main(String[] args)
{
    ObjectFactory of=new ObjectFactory();
   Resume resume=TestResume2.createResume(of);
    try {
        JAXBContext jax = JAXBContext.newInstance("target");
        TestResume2.serialize(jax,resume,"C:\\d2d\\myresume2.xml");
        if(TestResume2.deSerialize(jax,"C:\\d2d\\myresume2.xml")!=null)
        {
            TestResume2.printDetails(resume);
        }
    }
    catch (JAXBException e)
    {
        e.printStackTrace();
    }

}

}
