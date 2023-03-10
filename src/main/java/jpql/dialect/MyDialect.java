package jpql.dialect;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyDialect extends H2Dialect {

    public MyDialect() {
        registerFunction("group_concat",new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
