package agh.cs.oop.kubicki;

import agh.cs.oop.kubicki.functions.*;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Console {
    private String prompt;

    public Console(String prompt, Database db, String outputFilePath) throws IOException {
        this.prompt=prompt;

        Terminal terminal = TerminalBuilder.builder().system(true).build();
        LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
        HashMap<String, AbstractFunction> commands = loadCommands(db);
        String inputText;
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        while(true) {
            inputText = lineReader.readLine(this.prompt, (char) 0);
            if (inputText == "\n") continue;
            String output = "";
            String command =  "";
            String arg = "";
            if (inputText.contains(" ")){
                command = inputText.substring(0, inputText.indexOf(' '));
                arg = inputText.substring(inputText.indexOf(' ') + 1);
            } else {
                command = inputText;
            }
            if(commands.containsKey(command)) {
                AbstractFunction abstractFunction = commands.get(command);
                List<String> parsedArgs = parseArgs(arg);
                int size = 0;
                if (parsedArgs != null)
                    size = parsedArgs.size();
                if (abstractFunction instanceof Rubrum && size>0){
                    try {
                        output = abstractFunction.execute(parsedArgs);
                    }
                    catch (Exception ex){
                        output = "Niepoprawny argument!";
                    }
                    terminal.writer().println(output);
                }
                else if (size==abstractFunction.getNumberOfArgsRequired()) {
                    output = abstractFunction.execute(parsedArgs);
                    terminal.writer().println(output);
                } else{
                    terminal.writer().println("Niewłaściwa liczba argumentów! Wpisz 'help' aby uzyskać pomoc");
                }
            }
            else{
                output="Niepoprawna komenda. Wpisz 'help' aby wyświetlić pomoc";
                terminal.writer().println(output);
            }
            if(!outputFilePath.equals("")) {
                File file = new File(outputFilePath);
                fileWriter= new FileWriter(file, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(inputText+"\n");
                bufferedWriter.write(output+"\n");
                bufferedWriter.close();
                fileWriter.close();
            }
        }
    }

    public HashMap<String, AbstractFunction> loadCommands (Database db) {
        HashMap<String,AbstractFunction> commands = new HashMap<>();
        commands.put("rubrum", new Rubrum(db, 1));
        commands.put("content", new Content(db, 1));
        commands.put("judge", new JudgmentByJudge(db, 1));
        commands.put("judges", new Top10Judges(db, 0));
        commands.put("months", new Months(db, 0));
        commands.put("courts", new Courts(db, 0));
        commands.put("regulations", new Regulations(db, 0));
        commands.put("jury", new Jury(db,0));
        commands.put("exit", new Exit(db,0));
        commands.put("help", new Help(db,0));
        return commands;
    }

    public List<String> parseArgs(String args){
        if (args.equals("")) return null;
        List<String> parsedArgs = new ArrayList<>();
        int i =0;
        while (args.contains(",")){
            parsedArgs.add(i,args.substring(0, args.indexOf(',')));
            args = args.substring(args.indexOf(',') + 2);
            i++;
        }
        parsedArgs.add(i,args);
        return parsedArgs;
    }

}
