package cn.wolfcode.wms.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DRD
 * @date 2018年7月12日 下午12:41:45
 * @website www.wolfcode.cn
 * @description
 */
@Setter
@Getter
public class BaseDomain implements Serializable{
	private static final long serialVersionUID = -5591577014820744634L;
	private Long id;
}
