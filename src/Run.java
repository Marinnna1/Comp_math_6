import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Run {
    public static final HashMap<Integer,MusicBand> data = new HashMap<>();
    public static final Date date = new Date();
    public  static final File xml = new File("C:/kekk.xml");

    public static void main(String[] args) throws IOException {
        File newXml = new File("test.xml");
        readAndWrite(newXml);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(newXml);
            doc.getDocumentElement().normalize();
            System.out.println("Корневой элемент: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("MusicBand");
            NodeList nodeList2 = doc.getElementsByTagName("Coordinates");
            NodeList nodeList3 = doc.getElementsByTagName("Album");
            // создадим из него список объектов MusicBand
            for (int i = 0; i < nodeList.getLength(); i++) {
                MusicBand musicBand = new MusicBand();
                getMusicBand(musicBand, nodeList.item(i));
                musicBand.setCoordinates(getCoords(musicBand,nodeList2.item(i)));
                musicBand.setBestAlbum(getAlbum(musicBand,nodeList3.item(i)));
                data.put(i,musicBand);
            }

            // печатаем в консоль информацию по каждому объекту MusicBand
            for (Map.Entry<Integer,MusicBand> band : data.entrySet()) {
                System.out.println(band.getValue().toString());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        start();
    }

    public static void readAndWrite(File file) throws IOException {
        FileReader fileReader = new FileReader(xml);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int i = 0;
        while(fileReader.ready()){
            int data = fileReader.read();
            if (i==0){i++; continue;}
            fileOutputStream.write(data);
        }
        fileReader.close();
        fileOutputStream.close();
    }

    private static void getMusicBand(MusicBand musicBand, Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            musicBand.setName(getTagValue("name", element));
            musicBand.setNumberOfParticipants(Long.parseLong(getTagValue("numberOfParticipants", element)));
            musicBand.setSinglesCount(Long.parseLong(getTagValue("singles", element)));
            musicBand.setGenre(MusicGenre.valueOf(getTagValue("genre", element)));
        }
    }

    public static Coordinates getCoords(MusicBand musicBand, Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            Coordinates coordinates = new Coordinates(Float.parseFloat(getTagValue("x", element))
                    , Double.parseDouble(getTagValue("y", element)));
            return coordinates;
        }
        return null;
    }

    public static Album getAlbum(MusicBand musicBand, Node node){
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            Album album = new Album(getTagValue("albumName",element),
                    Integer.parseInt(getTagValue("length",element)));
            return album;
        }
        return null;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    public static void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean k = true;
        while(k) {
            String text = reader.readLine();
            String[] line = text.split(" ");
            switch (line[0]) {
                case "help":
                    printInfo();
                    continue;

                case "exit" : k=false; break;

                case "info" : //ст поток вывода о коллекции
                    continue;

                case "show" : // cт поток вывода
                    continue;


                default:
                    System.out.println("ты даун");
            }
        }
    }
    public static void printInfo(){
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("insert key {element} : добавить новый элемент с заданным ключом");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_key key : удалить элемент из коллекции по его ключу");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("exit : завершить программу (без сохранения в файл");
        System.out.println("remove_greater {element} : удалить из коллекции все элементы, превышающие заданный");
        System.out.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("replace_if_greater key {element} : заменить значение по ключу, если новое значение больше старого");
        System.out.println("remove_any_by_genre genre : удалить из коллекции один элемент, значение поля genre которого эквивалентно заданному");
        System.out.println("filter_greater_than_number_of_participants numberOfParticipants : вывести элементы, значение поля numberOfParticipants которых больше заданного");
        System.out.println("print_field_ascending_best_album bestAlbum : вывести значения поля bestAlbum в порядке возрастания");
    }
    }



