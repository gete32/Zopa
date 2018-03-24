package constants;

import java.math.BigDecimal;
import java.util.*;

public enum FileFieldsEnum {

    LENDER("Lender", String.class),
    RATE("Rate", BigDecimal.class),
    AVAILABLE("Available", Integer.class);

    private String name;
    private Class<?> aClass;

    private static Map<String, FileFieldsEnum> valueMap = null;

    FileFieldsEnum(String name, Class<?> aClass) {
        this.name = name;
        this.aClass = aClass;
    }

    private static void init(){
        if (valueMap != null) return;

        Map<String, FileFieldsEnum> map = new LinkedHashMap<>();
        for (FileFieldsEnum field : values())
            map.put(field.getName(), field);
        valueMap = Collections.unmodifiableMap(map);
    }

    public String getName() {
        return name;
    }

    public static FileFieldsEnum of(String value){
        init();
        return valueMap.get(value);
    }

    public static Set<String> getNames(){
        init();
        return valueMap.keySet();
    }

    public Class<?> getaClass() {
        return aClass;
    }
}
