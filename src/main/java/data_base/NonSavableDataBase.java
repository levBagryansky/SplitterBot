package data_base;

import logic.Split;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class NonSavableDataBase implements DataBase {

    private final Map<String, Split> base;

    NonSavableDataBase() {
        this(new ConcurrentHashMap<>());
    }

    NonSavableDataBase(final Map<String, Split> base) {
        this.base = base;
    }

    @Override
    public synchronized String addSplit(final Split split) {
        String code;
        do {
            code = RandomStringUtils.randomAlphanumeric(10);
        } while (this.base.containsKey(code));
        this.base.put(code, split);
        return code;
    }

    @Override
    public Split getSplit(final String code) throws SplitNotFoundException {
        return Optional.ofNullable(this.base.get(code))
            .orElseThrow(() -> new SplitNotFoundException(
                String.format("Element with code %s no found", code)
                )
            );
    }

}
