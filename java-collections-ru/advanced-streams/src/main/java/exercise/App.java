package exercise;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String configFile) {

        List<String> str = List.of(configFile.split("\n"));
        String str2 = str.stream()
                .filter(line -> line.startsWith("environment="))
                .map(line -> line.replaceAll("\"", ""))
                .map(line -> line.replaceAll("environment=", ""))
                .map(line -> line.split(","))
                .flatMap(line -> Arrays.stream(line))
                .filter(line -> line.startsWith("X_FORWARDED_"))
                .map(line -> line.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));

        return str2;
    }



       // List<String> str1 = List.of(str.split(","));
        /*List<String> str1 = str.stream()
                .map(s -> s.split(","))
                .collect(Collectors.toList());*/
        //List<String> str1 = str.split(",");
        //str.stream().forEach(s -> System.out.println("line " + s));
        //work
        /*str.stream()
                .filter(s -> s.startsWith("environment="))
                //.peek(System.out::println)
                .forEach(s -> System.out.println("line " + s));*/
        //work
        //List<String> str1 =


                                    //work >>>>>>>
                /*str.stream()
                .filter(s -> s.startsWith("environment="))
                        .map(s -> s.replaceAll("X_FORWARDED_", " "))
                        .forEach(s -> System.out.print(s));*/
                                    //work <<<<<<<<<<<<

        /*List<String> str1 = str.stream()
                .filter(s -> s.startsWith("environment="))
                .map(s -> s.replaceAll("environment=\"", ""))
                .collect(Collectors.toList());
        String str2 = str1.toString();
        System.out.println(str2);

        String str3 = str2.replaceAll(", ", "");
        System.out.println(str3);
        String str4 = str3.replaceAll("X_FORWARDED_", "\n");
        System.out.println(str4);*/
        /*List<String> x_forwarded_s = List.of(str3.split("X_FORWARDED_"));
        x_forwarded_s.stream()
                .forEach(s -> System.out.print(s));*/

        //.map(s -> Arrays.toString(s.split(" ")))
                //.map(s -> List.of(s.split(",")))
                //.map(s -> Arrays.toString(s.split(",")))
                //.forEach(s -> System.out.print(">>" + s));

        //.map(s -> Arrays.toString(s.split(" ")))
                        //.map(s -> )
                        //.map(s ->  + s.split(" "))

                        //.filter(s -> s.startsWith(" "))
                        //.map(s -> "" + s.split(" "))
                        //.collect(Collectors.toList());

        //str1.stream()

                //.forEach(s -> System.out.print(s.toString()));

                //.collect(Collectors.toList());
                        //.forEach();
                //.peek(System.out::println)
                //.collect(Collectors.toList());
        //System.out.println(str1.toArray());
        //str1.stream().forEach(s -> System.out.println("line " + s));
        //System.out.println(Arrays.toString(str1.toArray()));

        //String[] splitConfigFile = configFile.split("\n");
        /*System.out.println("hello2");
        str.stream().peek(s -> System.out.println(s.toString())).count();*/
        /*Arrays.stream(splitConfigFile)
                //.filter(str -> str.startsWith("environment="))
                .peek(str -> System.out.println(str));
        for (String str : splitConfigFile
             ) {
            System.out.println(str);
        }*/
       /* System.out.println("hello3");
        //int i =0;
        return "";*/
/*
    String[] lines = config.split("\n");
        return Arrays.stream(lines)
            .filter(line -> line.startsWith("environment="))
            .map(line -> line.replaceAll("environment=", ""))
            .map(line -> line.replaceAll("\"", ""))
            .map(line -> line.split(","))
            .flatMap(Arrays::stream)
            .filter(kv -> kv.startsWith("X_FORWARDED_"))
            .map(kv -> kv.replaceFirst("X_FORWARDED_", ""))
            .collect(Collectors.joining(","));
}*/
}
//END
