package hu.sonrisa.cc.functional.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

@Data
@Builder
public class LittleMoreComplexObject {
    private Pair<String, Integer> pairMember;
    private List<String> stringListMember;
}
