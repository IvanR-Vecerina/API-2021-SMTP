# **SMTP MailBot - Ivan Vecerina**
## **Introduction**
The objective of this project was to become familiar with the SMTP protocol and mock servers. To that end, we created an application allowing the user to send mass forged email to groups of people while impersonating one of them as the sender. This program was made as a pure proof of concept to show how easy it is to forge emails, not with ill intentions.

## **What is MockMock?**
MockMock is a cross-plateform mock SMTP server built on Java coupled with a web interface used to check if your emails get sent without actually sending them (mock) and seeing their content. Using it is a safe to make sure nothing is sent by accident.

You can learn more **[here](https://github.com/tweakers/MockMock)**.

## **How to setup you mock SMTP server with docker**
1. Clone MockMock from the link above and intall docker.
2. Build the executable jar file from the source file of the clone mockmock

        mvn clean install --file "<path>\MockMock\pom.xml"

3. Copy the executable jar file in the directory where you'll set up your docker file (execute from dockerfile directory)

        cp "<path>\MockMock\target\MockMock-1.4.0.one-jar.jar" .

4. Build the Docker image locally

        docker build --tag <yourInput>/mockmock

5. Setup your dockerfile as below (that's an example feel free to pick your own ports)

        FROM openjdk:11.0.13-jre
        MAINTAINER Ivan Vecerina

        # When we build the image, we copy the executable jar in the image file system. 
        #
        COPY MockMock-1.4.0.one-jar.jar /opt/app/MockMock.jar

        #
        # Our application will accept TCP connections on port 42069 and web interface on port 8282. Note that the EXPOSE statement
        # does not make the container accessible via the host. For the container to really be accessible,
        # we must either use the -p or the -P flag when using the docker run command. With -p, we can
        # specify an explicit port mapping (and EXPOSE is not even required). With -P, we let Docker 
        # assign random ports for each EXPOSEd port. We can then use the docker port command to know the port
        # numbers that have been selected.
        #
        EXPOSE 42069
        EXPOSE 8282

        #
        # This is the command that is executed when the Docker container starts
        #
        CMD ["java", "-jar", "/opt/app/MockMock.jar", "-p", "42069"]

6. Run the docker

        docker run -p 42069:42069 -p 8282:8282 ivecerina/mockmock

7. Open Docker and now you should see your mockmock docker running. Check http://localhost:8282/ for web interface (if you used same port as me).

## **How to use MailBot**
Before doing anything with MailBot, you'll need to clone this repo.
### <u>config/config.properties</u>
This is the file where you'll enter the parameters of the client(target server address and port, number of emails to send and witness email that'll be copied on all of them).

Template to follow:

    smtpServerAddress=YOURSmtpServerAddress
    smtpServerPort=YOURSmtpServerport
    numberOfGroups=NUMBER
    witnessesToCC=1@WITNESS.com, 2@WITNESS.com, ...

### <u>ressource/victims.utf8</u>
This is the file where you'll insert all the email addresses you want to target.

Template to follow:

    1@target.com
    2@target.com
    3@target.com
    ...

### <u>ressource/Spam Messages</u>
This is the directory where you'll put all the message file. Please make all their extentions ".utf8".
For the content of a message file (1 message per file btw) you'll need to follow the following template:

    Subject: YourSubject

    YourText--------
    ----------
    ...

### <u>Running the executable</u>
Now that you have everything setup, all that's left is to run the executable with the command:

    java -jar API-2021-SMTP.jar

If you've done everything properly, your Emails are sent.

## **Implementation**
We found it logical to split the program into 3 main packages:

- Config: handles and manages the configuration.
- Model:  handles everything related to the Email structure and data.
- SMTP:   handles everything related to the SMTP protocol and the client.