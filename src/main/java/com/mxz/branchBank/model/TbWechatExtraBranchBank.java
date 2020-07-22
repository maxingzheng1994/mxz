package com.mxz.branchBank.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/17 10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_wechat_extra_branch_bank")
public class TbWechatExtraBranchBank {
    @TableId(value = "n_id", type = IdType.AUTO)
    private Integer nId;

    @TableField(value = "n_branch_id")
    private String nBranchId;

    @TableField(value = "c_branch_name")
    private String cBranchName;
}