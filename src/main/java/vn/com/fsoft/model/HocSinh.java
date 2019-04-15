package vn.com.fsoft.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "HocSinh")
@Table(name = "HocSinh")
public class HocSinh {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Ma_Hoc_Sinh", length = 10)
	private Integer maHocSinh;

	@Column(name = "Ten_Hoc_Sinh", length = 30)
	private String tenHocSinh;

	@Column(name = "Ma_Lop", length = 5)
	private Integer maLop;

	@Column(name = "Username", length = 50)
	private String username;

	@Column(name = "Password", length = 20)
    private String password;

	@Column(name = "Role_Id", length = 5)
    private Integer roleId;

	@Column(name = "enabled")
	private String enabled;

	@Column(name="created_date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	private Date createdDate;

    @Column(name="created_by", length = 255)
    private String createdBy;

    @Column(name="updated_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    private Date updatedDate;

    @Column(name="updated_by", length = 255)
    private String updatedBy;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Role_Id", insertable = false, updatable = false)
    private Role role;

	@OneToMany(mappedBy = "hocSinh")
	private List<FileConverted> fileList;

	@Transient
	private List<Permission> permissionList;
}
