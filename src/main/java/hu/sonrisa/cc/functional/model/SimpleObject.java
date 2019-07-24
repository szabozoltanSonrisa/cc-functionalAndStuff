package hu.sonrisa.cc.functional.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SimpleObject {
    private String stringMember;
    private Integer intMember;
    private List<String> stringListMember;
}
