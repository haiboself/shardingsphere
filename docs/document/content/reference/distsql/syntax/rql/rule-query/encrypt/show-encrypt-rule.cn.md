+++
title = "SHOW ENCRYPT RULES"
weight = 2
+++

### 描述

`SHOW ENCRYPT RULES` 语法用于查询指定逻辑库中的数据加密规则。

### 语法

```sql
ShowEncryptRule::=
  'SHOW' 'ENCRYPT' 'RULES' ('FROM' databaseName)?

databaseName ::=
  identifier
```

### 补充说明

- 未指定 `databaseName` 时，默认是当前使用的 `DATABASE`。 如果也未使用 `DATABASE` 则会提示 `No database selected`。

### 返回值说明

| 列                        | 说明                |
| ------------------------- | ------------------ |
| table                     | 逻辑表名             |
| logic_column              | 逻辑列名             |
| logic_data_type           | 逻辑列数据类型        |
| cipher_column             | 密文列名             |
| cipher_data_type          | 密文列数据类型        |
| plain_column              | 明文列名             |
| plain_data_type           | 明文列数据类型        |
| assisted_query_column     | 辅助查询列名          |
| assisted_query_data_type  | 辅助查询列数据类型     |
| encryptor_type            | 加密算法类型          |
| encryptor_props           | 加密算法参数          |
| query_with_cipher_column  | 是否使用加密列进行查询  |

### 示例

- 查询指定逻辑库中的数据加密规则

```sql
SHOW ENCRYPT RULES FROM test1;
```

```sql
mysql> SHOW ENCRYPT RULES FROM test1;
+-------------+--------------+-----------------+---------------+------------------+--------------+-----------------+-----------------------+--------------------------+----------------+-------------------------+--------------------------+
| table       | logic_column | logic_data_type | cipher_column | cipher_data_type | plain_column | plain_data_type | assisted_query_column | assisted_query_data_type | encryptor_type | encryptor_props         | query_with_cipher_column |
+-------------+--------------+-----------------+---------------+------------------+--------------+-----------------+-----------------------+--------------------------+----------------+-------------------------+--------------------------+
| t_encrypt   | user_id      |                 | user_cipher   |                  | user_plain   |                 |                       |                          | AES            | aes-key-value=123456abc | true                     |
| t_encrypt   | order_id     |                 | order_cipher  |                  |              |                 |                       |                          | MD5            |                         | true                     |
| t_encrypt_2 | user_id      |                 | user_cipher   |                  | user_plain   |                 |                       |                          | AES            | aes-key-value=123456abc | false                    |
| t_encrypt_2 | order_id     |                 | order_cipher  |                  |              |                 |                       |                          | MD5            |                         | false                    |
+-------------+--------------+-----------------+---------------+------------------+--------------+-----------------+-----------------------+--------------------------+----------------+-------------------------+--------------------------+
4 rows in set (0.00 sec)
```

- 查询当前逻辑库中的数据加密规则

```sql
SHOW ENCRYPT RULES;
```

```sql
mysql> SHOW ENCRYPT RULES;
+-------------+--------------+-----------------+---------------+------------------+--------------+-----------------+-----------------------+--------------------------+----------------+-------------------------+--------------------------+
| table       | logic_column | logic_data_type | cipher_column | cipher_data_type | plain_column | plain_data_type | assisted_query_column | assisted_query_data_type | encryptor_type | encryptor_props         | query_with_cipher_column |
+-------------+--------------+-----------------+---------------+------------------+--------------+-----------------+-----------------------+--------------------------+----------------+-------------------------+--------------------------+
| t_encrypt   | user_id      |                 | user_cipher   |                  | user_plain   |                 |                       |                          | AES            | aes-key-value=123456abc | true                     |
| t_encrypt   | order_id     |                 | order_cipher  |                  |              |                 |                       |                          | MD5            |                         | true                     |
| t_encrypt_2 | user_id      |                 | user_cipher   |                  | user_plain   |                 |                       |                          | AES            | aes-key-value=123456abc | false                    |
| t_encrypt_2 | order_id     |                 | order_cipher  |                  |              |                 |                       |                          | MD5            |                         | false                    |
+-------------+--------------+-----------------+---------------+------------------+--------------+-----------------+-----------------------+--------------------------+----------------+-------------------------+--------------------------+
4 rows in set (0.00 sec)
```

### 保留字

`SHOW`、`ENCRYPT`、`RULES`、`FROM`

### 相关链接

- [保留字](/cn/reference/distsql/syntax/reserved-word/)

