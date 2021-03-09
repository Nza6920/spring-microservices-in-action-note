package organization.model;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 机构
 *
 * @author Zian.Niu
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "organization")
public class Organization {

    /**
     * ID
     */
    @Id
    @Column(name = "id", nullable = false)
    String id;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    String name;

    /**
     * 联系人
     */
    @Column(name = "contact_name", nullable = false)
    String contactName;

    /**
     * 联系Email
     */
    @Column(name = "contact_email", nullable = false)
    String contactEmail;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone", nullable = false)
    String contactPhone;
}
