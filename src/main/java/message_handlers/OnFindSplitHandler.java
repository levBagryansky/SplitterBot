package message_handlers;

import data_base.DataBase;
import data_base.SplitNotFoundException;
import logic.Split;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class OnFindSplitHandler implements MessageHandler {

    @Override
    public Pair<MessageHandler, SendMessage> handle(final Message message) {
        return Pair.of(
            answer -> {
                final String tag = answer.getText();
                try{
                    final Split found = DataBase.instance.getSplit(answer.getText());
                    return Pair.of(
                        new ConfiguringSplitHandler(found),
                        MessageHandler.messageFromString(
                            "Текущий сплит: %s\nВы можете добавлять чеки с помощью %s или распечатать текущие результаты с помощью %s",
                            found.toString(),
                            BotCommands.STRINGS.ADD_EVENT,
                            BotCommands.STRINGS.RESULTS
                        )
                    );
                } catch (final SplitNotFoundException exc) {
                    System.out.println(exc);
                    return Pair.of(
                        new Menu(),
                        MessageHandler.messageFromString(
                            "Не могу найти  сплит %s. Скорее всего он некорректен или я его удалил :(. Попробуйте заново, всё получится. Введите %s для создания сплита или %s для нахождения существующего.",
                            tag,
                            BotCommands.STRINGS.INIT_SPLIT,
                            BotCommands.STRINGS.FIND_SPLIT
                        )
                    );
                }
            },
            MessageHandler.messageFromString("Введите тэг, который вам прислал другой участник сплита")
        );
    }
}
