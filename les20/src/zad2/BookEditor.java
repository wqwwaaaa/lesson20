package zad2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class BookEditor {
    private Function<String, String> headerDecorator;
    private List<Function<String, String>> lineProcessors = new ArrayList<>();

    public static void main(String[] args) {
        BookEditor bookEditor = new BookEditor();

        bookEditor.setHeaderDecorator(header -> header.toUpperCase() + "\n");
        bookEditor.addLineProcessor(line -> line.substring(0, 1).toUpperCase() + line.substring(1));
        bookEditor.addLineProcessor(line -> line + "\n");

        List<String> content = Arrays.asList(
                "Приключения Java-программиста",
                "История началась рано утром, ",
                "когда программист вышел из дома, ",
                "решив выпить утренний кофе."
        );
        List<String> resultContent = bookEditor.processText(content);
        System.out.println(resultContent);
    }
    public List<String> processText(List<String> sourceText) {
        List<String> resultText = new ArrayList<>();
        String sourceHeader = sourceText.get(0);
        String decoratedHeader = headerDecorator.apply(sourceHeader);
        resultText.add(decoratedHeader);
        for (int i=1; i<sourceText.size(); i++) {
            String currentLine = sourceText.get(i);
            for (Function<String, String> processor: lineProcessors) {
                currentLine = processor.apply(currentLine);
            }
            resultText.add(currentLine);
        }
        return resultText;
    }
    public void setHeaderDecorator(Function<String, String> headerDecorator) {
        this.headerDecorator = headerDecorator;
    }
    public void addLineProcessor(Function<String, String> lineProcessor) {
        this.lineProcessors.add(lineProcessor);
    }
}
