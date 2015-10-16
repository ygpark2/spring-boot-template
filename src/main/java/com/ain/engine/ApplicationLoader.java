package com.ain.engine;

import com.ain.engine.domain.csos.Address;
import com.ain.engine.domain.csos.Customer;
import com.ain.engine.jms.MsgReceiver;
import com.ain.engine.repository.csos.AddressRepository;
import com.ain.engine.repository.csos.CustomerRepository;
import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

/**
 * Hello world!
 */
@SpringBootApplication
// @JmxAutoConfiguration
@EnableJms
@EnableScheduling
// @Configuration
// @EnableAutoConfiguration
// @ComponentScan(basePackages = "com.kdn.csos.engine")
@EnableAdminServer
@Log4j2
public class ApplicationLoader implements CommandLineRunner {

    // private static final Logger logger = LoggerFactory.getLogger(ApplicationLoader.class);

    private ClassPathResource xmlFileRsc = new ClassPathResource("/csos.xml");
    private Map<String, Object> engines = new HashMap<String, Object>();
    private int rcpPort = -1;

    @Autowired
    private ApplicationContext context;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationLoader.class, args);

        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello");
            }
        };

        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
        jmsTemplate.send("message-queue", messageCreator);

    }

    @Override
    public void run(String... args) throws Exception {

        MsgReceiver adapter = (MsgReceiver) context.getBean("msgReceiver");

        log.error("Message logged at ERROR level");
        log.warn("Message logged at WARN level");
        log.info("Message logged at INFO level");
        log.debug("Message logged at DEBUG level");
        log.debug("========================= Application Loader Run ====================================");
        for (String c : context.getBeanDefinitionNames()){
            System.out.println(c);
        }
        System.out.println("---------------------------------------------" );
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFileRsc.getInputStream());

        // Engine 처리
        NodeList engineList = doc.getElementsByTagName("engine");
        for(int i=0; i < engineList.getLength(); i++) {
            // 프로토콜 버전 취득
            Node version = engineList.item(i).getAttributes().getNamedItem("version");

            // Engine ID 와 Class 취득
            String key = engineList.item(i).getAttributes().getNamedItem("id").getNodeValue();
            String cls = engineList.item(i).getAttributes().getNamedItem("class").getNodeValue();

            Node nodeLogging = engineList.item(i).getAttributes().getNamedItem("logging");
            Node cmds = engineList.item(i).getAttributes().getNamedItem("cmds");

            if (cls.startsWith("com.kdn.csos.engine.core")) {

                // @SuppressWarnings("unchecked")
                // Class<BaseProtocolParser> c = (Class<BaseProtocolParser>) Class.forName(cls);
                // Constructor<BaseProtocolParser> cons = c.getConstructor(new Class[]{EngineProperties.class, String.class});
                // BaseProtocolParser bpp = (BaseProtocolParser) cons.newInstance(ep, key);

                if (engines.containsKey(key)) {
                    engines.remove(key);
                }

                // adapter.getEngines().put(key, newCsosProtocolParser);
            } else {
                throw new Exception("Invalid class name.(Class name is " + cls + ".)");
            }
        }








        Customer customer = new Customer();
        customer.setName("Foo");
        Customer c2 = customerRepository.save(customer);

        System.out.println("-------- end of customer save --------" + customerRepository.count());
        for (Customer c : customerRepository.findAll()){
            System.out.println(c.getName());
        }

        Customer c = customerRepository.findByName("Foo");
        System.out.println("-------- end of customer findByName --------");

        Address a = new Address();
        a.setLine1("abc");
        a.setCustomer(c);
        addressRepository.save(a);
        System.out.println("-------- end of address save --------");

        /*

        */
    }

}
