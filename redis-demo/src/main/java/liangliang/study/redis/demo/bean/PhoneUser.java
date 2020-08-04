package liangliang.study.redis.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhoneUser implements Serializable {

    private String phone;
    private Double fare;

    // 手机号相同，代表充值记录相同，仅适用于特殊的排名需要，所以需要重写equals和hashCode方法


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneUser phoneUser = (PhoneUser) o;
        return Objects.equals(phone, phoneUser.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
