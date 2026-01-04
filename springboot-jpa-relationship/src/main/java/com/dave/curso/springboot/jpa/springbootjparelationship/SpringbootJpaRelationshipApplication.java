package com.dave.curso.springboot.jpa.springbootjparelationship;

import com.dave.curso.springboot.jpa.springbootjparelationship.entities.Address;
import com.dave.curso.springboot.jpa.springbootjparelationship.entities.Client;
import com.dave.curso.springboot.jpa.springbootjparelationship.entities.ClientDetails;
import com.dave.curso.springboot.jpa.springbootjparelationship.entities.Invoice;
import com.dave.curso.springboot.jpa.springbootjparelationship.repositories.ClientDetailsRepository;
import com.dave.curso.springboot.jpa.springbootjparelationship.repositories.ClientRepository;
import com.dave.curso.springboot.jpa.springbootjparelationship.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        oneToOne();
    }

    @Transactional
    public void oneToOne(){
        Client client = new Client("Erba", "Pura");
        clientRepository.save(client);

        ClientDetails clientDetails = new ClientDetails(false, 5000);
        clientDetails.setClient(client);
        clientDetailsRepository.save(clientDetails);
    }

    @Transactional
    public void removeInvoiceBidireccionalFindById(){
        Optional<Client> optionalClient = clientRepository.findById(1L);
        optionalClient.ifPresent(client -> {
            Invoice invoice1 = new Invoice("compras de la casa", 2667L);
            Invoice invoice2 = new Invoice("compras de la oficina", 6784L);

            client.addInvoice(invoice1)
                    .addInvoice(invoice2);

            clientRepository.save(client);
            System.out.println(client);
        });
        Optional<Client> optionalClientBD = clientRepository.findById(1L);
        optionalClientBD.ifPresent(client -> {
            Optional<Invoice> invoiceOptional = invoiceRepository.findById(2L);
            invoiceOptional.ifPresent(invoice -> {
                client.removeInvoce(invoice);
                clientRepository.save(client);
                System.out.println(client);
            });
        });
    }

    @Transactional
    public void oneToManyBidireccionalFindById(){
        Optional<Client> optionalClient = clientRepository.findById(1L);
        optionalClient.ifPresent(client -> {
            Invoice invoice1 = new Invoice("compras de la casa", 2667L);
            Invoice invoice2 = new Invoice("compras de la oficina", 6784L);

            client.addInvoice(invoice1)
                            .addInvoice(invoice2);

            clientRepository.save(client);
            System.out.println(client);
        });
    }

    @Transactional
    public void oneToManyBidireccional(){
        Client client = new Client("Fran", "Moras");
        Invoice invoice1 = new Invoice("compras de la casa", 2667L);
        Invoice invoice2 = new Invoice("compras de la oficina", 6784L);

        client.addInvoice(invoice1)
                .addInvoice(invoice2);

        clientRepository.save(client);
        System.out.println(client);
    }

    @Transactional
    public void removeAdressFindById(){
        Optional<Client> optionalClient = clientRepository.findById(2L);
        optionalClient.ifPresent(client -> {
            Address address1 = new Address("La Fachada", 3145);
            Address address2 = new Address("El Granada", 5156);

            client.getAddresses().add(address1);
            client.getAddresses().add(address2);

            clientRepository.save(client);
            System.out.println(client);

            Optional<Client> optionalClient2 = clientRepository.findOne(2L);
            optionalClient2.ifPresent(c -> {
                c.getAddresses().remove(1);
                clientRepository.save(c);
                System.out.println(c);
            });
        });
    }

    @Transactional
    public void removeAdress(){
        Client client = new Client("Fran", "Moras");

        Address address1 = new Address("La Fachada", 3145);
        Address address2 = new Address("El Granada", 5156);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        clientRepository.save(client);
        System.out.println(client);

        Optional<Client> optionalClient = clientRepository.findById(3L);
        optionalClient.ifPresent(c -> {
            c.getAddresses().remove(address1);
            clientRepository.save(c);
            System.out.println(c);
        });
    }

    @Transactional
    public void oneToMany(){
        Client client = new Client("Fran", "Moras");
        Address address1 = new Address("La Fachada", 3145);
        Address address2 = new Address("El Granada", 5156);

        client.getAddresses().add(address1);
        client.getAddresses().add(address2);

        clientRepository.save(client);

        System.out.println(client);
    }

    @Transactional
    public void oneToManyFindById(){
        Optional<Client> optionalClient = clientRepository.findById(2L);
        optionalClient.ifPresent(client -> {
        Address address1 = new Address("La Fachada", 3145);
        Address address2 = new Address("El Granada", 5156);

        client.setAddresses(Arrays.asList(address1, address2));

        clientRepository.save(client);
        System.out.println(client);
        });
    }

    @Transactional
    public void manyToOne(){
        Client client = new Client("John", "Doe");
        clientRepository.save(client);

        Invoice invoice = new Invoice("Compras de oficina", 2000L);
        invoice.setClient(client);
        Invoice invoiceDB = invoiceRepository.save(invoice);

        System.out.println(invoiceDB);
    }

    @Transactional
    public void manyToOneFindByIdClient(){
        Optional<Client> optionalClient = clientRepository.findById(1L);
        if(optionalClient.isPresent()){
            Client client = optionalClient.orElseThrow();
            Invoice invoice = new Invoice("Compras de oficina", 2000L);
            invoice.setClient(client);
            Invoice invoiceDB = invoiceRepository.save(invoice);
            System.out.println(invoiceDB);
        }
    }
}
