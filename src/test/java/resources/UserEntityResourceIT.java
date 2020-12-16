package resources;

import org.example.App;
import org.example.domain.Dao;
import org.example.domain.UserEntity;
import org.example.domain.UserDaoMock;
import org.example.resources.JsonResource;
import org.example.resources.UserResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.net.URL;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class UserEntityResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    private String contactsResource;
    private String contactsUri = "api/contacts";

    @Before
    public void setup() {
        contactsResource = deploymentURL + contactsUri;
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "ContactsResourceIT.war")
                .addClass(App.class) // dont forget!
                .addClass(JsonResource.class)
                .addClass(UserResource.class)
                .addClass(UserEntity.class)
                .addClass(Dao.class)
                .addClass(UserDaoMock.class)
                .addAsWebInfResource("test-beans.xml", "beans.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");

        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void whenContactIsPostedICanGetIt() {
        Client http = ClientBuilder.newClient();
        UserEntity ue = UserEntity.builder().id("1").email("bartgeest@live.nl").password("Smith").build();

        String postedContact = http
                .target(contactsResource)
                .request().post(entity(ue, APPLICATION_JSON), String.class);

        System.out.println(postedContact);

        String allContacts = http
                .target(contactsResource)
                .request().get(String.class);

        System.out.println(allContacts);

        assertThat(allContacts, containsString("\"id\":\"1\""));
        assertThat(allContacts, containsString("\"email\":\"bartgeest@live.nl\""));
        assertThat(allContacts, containsString("\"password\":\"Smith\""));
    }
}
