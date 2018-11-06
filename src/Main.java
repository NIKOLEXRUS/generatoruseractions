import model.User;
import model.UserAction;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Main {


    public static final int COUNTUSER = 10;
    public static final int COUNTACTIONS = 10;

    public static void main(String[] args) throws ParserConfigurationException {
        String[] firstNames = {"Андрей", "Борис", "Александр", "Дмитрий", "Евгений", "Федор", "Геннадий", "Харитон", "Иннокентий", "Евгений", "Николай"};
        String[] lastNames = {"Иванов", "Петров", "Сидоров", "Кузнецов", "Воронцов", "Петин", "Лурицкий", "Бородин", "Лужков", "Пушков", "Трубецкой"};
        String[] middleName = {"Иванович", "Петрович", "Сидорович", "Дмитриевич", "Андреевич", "Федорович", "Геннадьевич", "Николаевич", "Александрович", "Борисович", "Харитонович"};
        String[] positions = {"Ведущий специалист", "Секретарь", "Программист", "Ведущий программист"};
        String[] status = {"Устроен на работу", "Уволен", "Отпуск", "Декрет", "Больничный", "Отгул"};
        String[] dep1 = {"Департамент кадровой политики", "Департамент управления делами", "Департамент организации и контроля", "Департамент управления департаментами", "Департамент логистики"};
        String[] dep2 = {"Департамент очищения", "Департамент улучшения", null};
        String[] dep3 = {"Отдел кадров", "Отдел по Управлению Имуществом", "Отдел докуменентационного обеспечения", "Отдел разработки", "Отдел распределения средств"};
        LocalDate d1 = LocalDate.of(2010, 8, 4);
        LocalDate d2 = LocalDate.of(2011, 07, 8);
        LocalDate d3 = LocalDate.of(2012, 11, 23);
        LocalDate d4 = LocalDate.of(2013, 10, 20);
        LocalDate d5 = LocalDate.of(2014, 5, 14);
        LocalDate d6 = LocalDate.of(2015, 10, 22);
        LocalDate d7 = LocalDate.of(2016, 9, 10);
        LocalDate d8 = LocalDate.of(2017, 10, 14);
        LocalDate d9 = LocalDate.of(2010, 2, 16);
        LocalDate d10 = LocalDate.of(2011, 6, 17);
        LocalDate d11 = LocalDate.of(2012, 3, 22);
        LocalDate d12 = LocalDate.of(2013, 1, 9);
        LocalDate[] datestart = {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12};
        LocalDate[] dateend = {d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, null};

        List<User> userList = new ArrayList<>();
        List<UserAction> userActions = new ArrayList<>();
        //Генерация пользователей
        for (int i = 0; i < COUNTUSER; i++) {
            User ua = new User(UUID.randomUUID(),getRandom(lastNames), getRandom(firstNames),getRandom(middleName) );
            userList.add(ua);
        }
        //Генерация действий пользователя
        for (User user : userList) {
            for (int j = 0; j < COUNTACTIONS; j++) {
                UserAction aa = new UserAction();
                aa.setUser(user);
                aa.setStatus(getRandom(status));
                aa.setPosition(getRandom(positions));
                aa.setStartDate(getRandom(datestart));
                aa.setEndDate(getRandom(dateend));
                aa.setDepartamentOne(getRandom(dep1));
                aa.setDepartamentTwo(getRandom(dep2));
                aa.setDepartamentThree(getRandom(dep3));
                userActions.add(aa);
            }
        }
        createXML(userActions);
    }

    public static <T> T getRandom(T[] items) {
        int SIZE = items.length;

        Random RANDOM = new Random();
        return items[RANDOM.nextInt(SIZE)];

    }

    private static void createXML(List<UserAction> item) throws TransformerFactoryConfigurationError, DOMException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document document = docBuilder.newDocument();
        Element rootElement = document.createElement("Empoloyees");
        document.appendChild(rootElement);

        // Создаем новую книгу по элементам
        // Сама книга <Book>
        for (UserAction itemua : item) {
            Element Empoloyee = document.createElement("Empoloyee");

            Element id = document.createElement("Id");
            id.setTextContent(itemua.getUser().getId().toString());

            Element lastName = document.createElement("LastName");
            lastName.setTextContent(itemua.getUser().getLastName());
            // <Title>
            Element firstName = document.createElement("FirstName");
            firstName.setTextContent(itemua.getUser().getFirstName());

            Element middleName = document.createElement("MiddleName");
            middleName.setTextContent(itemua.getUser().getMiddleName());
            // Устанавливаем значение текста внутри тега
            Element status = document.createElement("Status");
            status.setTextContent(itemua.getStatus());
            Element position = document.createElement("Position");
            position.setTextContent(itemua.getPosition());

            Element dep1 = document.createElement("DepartamentLvl1");
            dep1.setTextContent(itemua.getDepartamentOne());

            Element dep2 = document.createElement("DepartamentLvl2");
            dep2.setTextContent(itemua.getDepartamentTwo());

            Element dep3 = document.createElement("DepartamentLvl3");
            dep3.setTextContent(itemua.getDepartamentThree());

            // Устанавливаем значение текста внутри тега
            Element startDate = document.createElement("StartDate");
            startDate.setTextContent(itemua.getStartDate().toString());

            Element endDate = document.createElement("EndDate");
            if (itemua.getEndDate() != null) {
                endDate.setTextContent(itemua.getEndDate().toString());
            }

            Empoloyee.appendChild(id);
            Empoloyee.appendChild(lastName);
            Empoloyee.appendChild(firstName);
            Empoloyee.appendChild(middleName);
            Empoloyee.appendChild(status);
            Empoloyee.appendChild(position);
            Empoloyee.appendChild(dep1);
            Empoloyee.appendChild(dep2);
            Empoloyee.appendChild(dep3);
            Empoloyee.appendChild(startDate);
            Empoloyee.appendChild(endDate);

            rootElement.appendChild(Empoloyee);
        }

        // Добавляем книгу в корневой элемент
        //root.appendChild(book);

        // Записываем XML в файл
        writeDocument(document);
    }

    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
