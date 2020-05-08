create database DB_ProjectSem2_CoffeeManagement
go

use DB_ProjectSem2_CoffeeManagement
go

create table UserAccount(
	UserId int identity primary key,
	Fullname nvarchar(100),
	Username varchar(50) unique not null,
	Password varchar(50) not null,
	PhoneNumber varchar(10) unique,
	Email varchar(50) unique,
	Gender bit,
	Address nvarchar(200),
	Status bit,
	ImageUser text default NULL,
	CreatedDate datetime,
	UpdatedDate datetime
)
go

create table Customer(
	CusId int identity primary key,
	Fullname nvarchar(100),
	PhoneNumber varchar(10),
	Email varchar(50),
	Gender bit,
	ArriveDate Date,
	ArriveHour Time,
	Status tinyint, /* 0: Khách đã đặt; 1:Khách đã đến; 2:Khách đã thanh toán; 3:Khách không đến; 4:Khách hủy */
	CreatedDate datetime,
	UpdatedDate datetime
)
go

create table Area (
	AreaId int identity primary key,
	AreaName nvarchar(50) unique not null,
	CreatedDate datetime,
	UpdatedDate datetime
)

create table CoffeeTable(
	TableId int identity primary key,
	TableName nvarchar(50) not null,
	AreaId int foreign key references Area(AreaId),
	Status tinyint,
	CreatedDate datetime,
	UpdatedDate datetime
)
go

create table Category(
	CateId int identity primary key,
	CateName nvarchar(100) unique not null,
	CreatedDate datetime,
	UpdatedDate datetime
)
go

create table Product(
	ProId int identity primary key,
	ProName nvarchar(200) unique not null,
	CateId int foreign key references Category(CateId),
	Price float,
	Quantity int,
	ProImage nvarchar(200),
	CreatedDate datetime,
	UpdatedDate datetime
)
go

create table TableProduct(
	TblProId int identity primary key,
	ProId int foreign key references Product(ProId),
	TableId int foreign key references CoffeeTable(TableId),
	QuantityOrder int,
	Status tinyint,/*0:đã đặt;1:đã thanh toán*/
	CreatedDate datetime,
	UpdatedDate datetime
)
go

create table OrderCoffee(
	OrderId int identity primary key,
	UserId int foreign key references UserAccount(UserId) not null,
	UserCashier int foreign key references UserAccount(UserId) null,
	CusId int foreign key references Customer(CusId) null,
	TotalMoney float,
	Status tinyint,
	CreatedDate datetime,
	UpdatedDate datetime
)
go

create table OrderDetail(
	OrderDetailId int identity primary key,
	OrderId int foreign key references OrderCoffee(OrderId),
	TableId int foreign key references CoffeeTable(TableId),
	Status tinyint,
	CreatedDate datetime,
	UpdatedDate datetime
)
go

insert into UserAccount values(N'Administrator','admin','123456','0123456789','admin@gmail.com','1',N'Admin home','1',NULL,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
insert into UserAccount values(N'Trần Quốc Đạt','dat69','123456','0961669699','trandat@gmail.com','1',N'Hà Nội','1',NULL,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
insert into UserAccount values(N'Võ Như Nhất','nhat69','123456','0355388343','nhatvo@gmail.com','1',N'Phú Thọ','1',NULL,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)
go

create proc findUserByUsername
@Username varchar(50)
as
select * from UserAccount where Username = @Username
go

create proc createNewUser
	@Fullname nvarchar(100),
	@Username varchar(50),
	@Password varchar(50),
	@PhoneNumber varchar(10),
	@Email varchar(50),
	@Gender bit,
	@Address nvarchar(200),
	@CreatedDate datetime,
	@ImageUser text
as
insert into UserAccount values(@Fullname,@Username,@Password,@Phonenumber,@Email, @Gender,@Address,1,@ImageUser,@CreatedDate,NULL)
go

create proc findPhoneNumber
@PhoneNumber varchar(10)
as
select * from UserAccount where PhoneNumber = @PhoneNumber
go

create proc findEmail
@Email varchar(50)
as
select * from UserAccount where Email = @Email
go

create proc getAllUsers
as
select * from UserAccount where UserId > 1
go

create proc updateUser
	@UserId int,
	@Fullname nvarchar(100),
	@Username varchar(50),
	@Password varchar(50),
	@PhoneNumber varchar(10),
	@Email varchar(50),
	@Gender bit,
	@Address nvarchar(200),
	@CreatedDate datetime,
	@ImageUser text
as
update UserAccount set Fullname = @Fullname, Username = @Username, Password = @Password,PhoneNumber = @PhoneNumber,Email = @Email,Gender = @Gender,Address = @Address, CreatedDate = @CreatedDate,ImageUser=@ImageUser where UserId = @UserId
go

create proc checkExistUsername
@OldUsername nvarchar(50),
@NewUsername nvarchar(50)
as
select * from UserAccount where @NewUsername in (select Username from UserAccount where Username != @OldUsername)
go

create proc checkExistPhoneNumber
@OldPhoneNumber nvarchar(50),
@NewPhoneNumber nvarchar(50)
as
select * from UserAccount where @NewPhoneNumber in (select PhoneNumber from UserAccount where PhoneNumber != @OldPhoneNumber)
go

create proc checkExistEmail
@OldEmail nvarchar(50),
@NewEmail nvarchar(50)
as
select * from UserAccount where @NewEmail in (select Email from UserAccount where Email != @OldEmail)
go

create proc deleteUser
@UserName varchar(50)
as 
delete from UserAccount where Username = @UserName
go

create proc updatePasswordUser
	@UserId int,
	@Password varchar(50)
as
update UserAccount set Password = @Password where UserId = @UserId
go

create proc getUserByUserId
@UserId int
as
select * from UserAccount where UserId = @UserId
go


create proc InsertCategory
	@CateName nvarchar(100)
as
begin
	insert into Category values (@CateName, GETDATE(), NULL)
end
go

create proc UpdateCategory
	@CateName nvarchar(100),
	@CateId int
as
begin
	update Category set CateName = @CateName, UpdatedDate = GETDATE() where CateId = @CateId
end
go

create proc DeleteCategory
	@CateId int
as
begin
	delete from Category where CateId = @CateId
end
go

create proc DeleteCategoryAndProduct
	@CateId int,
	@CateName nvarchar(100)
as
begin
	delete from Product where CateId = @CateId
	delete from Category where CateName = @CateName
end
go

create proc ListOfCategory
as
begin
	select * from Category
end
go

create proc FindCategoryById
	@CateId int
as
begin
	select * from Category where CateId = @CateId
end
go

create proc FindCategoryByCateName
	@CateName nvarchar(100)
as
begin
	select * from Category where CateName = @CateName
end
go

create proc CheckExistCategory
	@OldCateName nvarchar(100),
	@NewCateName nvarchar(100)
as
begin
	select * from Category where @NewCateName in (select CateName from Category where CateName != @OldCateName)
end
go

create proc InsertNewProduct
	@ProName nvarchar(200),
	@CateId int,
	@Price float,
	@Quantity int,
	@ProImage nvarchar(200)
as
begin
	insert into Product values (@ProName, @CateId, @Price, @Quantity, @ProImage, GETDATE(), NULL)
end
go

create proc UpdateProduct
	@ProName nvarchar(200),
	@CateId int,
	@Price float,
	@Quantity int,
	@ProImage nvarchar(200),
	@ProId int
as
begin
	update Product set ProName = @ProName, CateId = @CateId, Price = @Price, Quantity = @Quantity, ProImage = @ProImage, UpdatedDate = GETDATE() where ProId = @ProId
end
go

create proc DeleteProduct
	@ProId int
as
begin
	delete from Product where ProId = @ProId
end
go

create proc ListOfProduct
as
begin
	select * from Product
end
go

create proc FindProductByProName
	@ProName nvarchar(200)
as
begin
	select * from Product where ProName = @ProName
end
go

create proc CheckExistProduct
	@OldProName nvarchar(200),
	@NewProName nvarchar(200)
as
begin
	select * from Product where @NewProName in (select ProName from Product where ProName != @OldProName)
end
go

create proc LoadProductByCategory
	@CateId int
as
begin
	select * from Product where CateId = @CateId
end
go

create proc SearchProductByName
	@ProName nvarchar(200)
as
begin
	select * from Product where ProName like @ProName
end
go

create proc FindProductByProId
	@ProId int
as
begin
	select * from Product where ProId = @ProId
end
go

create proc updateQuantityProductClickOrder
@ProId int
as
update Product set Quantity = Quantity-1 where ProId = @ProId
go 

create proc updatePlusQuantityProduct
@ProId int,
@Quantity int
as
update Product set Quantity = Quantity + @Quantity where ProId = @ProId
go

create proc updateSubstractQuantityProduct
@ProId int,
@Quantity int
as
update Product set Quantity = Quantity - @Quantity where ProId = @ProId
go




create proc getAllAreas
as
select * from Area
go

create proc createNewArea
	@AreaName nvarchar(50)
as
insert into Area values(@AreaName,CURRENT_TIMESTAMP,NULL)
go

create proc checkExistArea
@OldAreaName nvarchar(50),
@NewAreaName nvarchar(50)
as
select * from Area where @NewAreaName in (select AreaName from Area where AreaName != @OldAreaName)
go

create proc updateArea
@AreaId int,
@AreaName nvarchar(50)
as
update Area set AreaName = @AreaName, UpdatedDate = CURRENT_TIMESTAMP where AreaId = @AreaId
go

create proc deleteArea
@AreaId int,
@AreaName nvarchar(50)
as
delete from CoffeeTable where AreaId = @AreaId
delete from Area where AreaName = @AreaName
go

create proc findAreaById
@AreaId int
as
select * from Area where AreaId = @AreaId
go

create proc createNewCoffeeTable
	@TableName nvarchar(50),
	@AreaId int
	as
	insert into CoffeeTable values(@TableName,@AreaId,0,CURRENT_TIMESTAMP,NULL)
	go

create proc getAllCoffeeTables
@AreaId int
as
select * from CoffeeTable where AreaId = @AreaId
go

create proc getAllCoffeeTablesWithoutSelectedTable
@AreaId int,
@TableId int
as
select * from CoffeeTable where AreaId = @AreaId and TableId != @TableId
go

create proc checkExistCoffeeTable
@OldCoffeeTable nvarchar(50),
@NewCoffeeTable nvarchar(50),
@AreaId int
as
select * from CoffeeTable where @NewCoffeeTable in (select TableName from CoffeeTable where TableName != @OldCoffeeTable and AreaId = @AreaId)
go

create proc checkExistCoffeeTableInArea
@NewCoffeeTable nvarchar(50),
@AreaId int
as
select * from CoffeeTable where @NewCoffeeTable in (select TableName from CoffeeTable where AreaId != @AreaId)
go

create proc updateCoffeeTable
	@TableId int,
	@TableName nvarchar(50),
	@AreaId int
as
update CoffeeTable set TableName = @TableName, AreaId = @AreaId,UpdatedDate = CURRENT_TIMESTAMP where TableId = @TableId
go

create proc deleteCoffeeTable
	@TableId int
as
delete from CoffeeTable where TableId= @TableId
go

create proc getQuantityCoffeeTableInArea
@AreaId int
as
select count(TableName) as Quantity from CoffeeTable where AreaId = @AreaId
go

create proc getCoffeeTableByTableId
@TableId int
as
select * from CoffeeTable where TableId = @TableId
go

create proc updateStatusCoffeeTable
	@Status int,
	@TableId int
as
update CoffeeTable set Status = @Status where TableId = @TableId
go

create proc getAllCoffeeTablesByStatus
@AreaId int,
@Status int
as
select * from CoffeeTable where AreaId = @AreaId and Status = @Status
go

create proc getAllCoffeeTablesByTwoStatus
@AreaId int,
@StatusOne int,
@StatusTwo int
as
select * from CoffeeTable where AreaId = @AreaId and (Status = @StatusOne OR Status = @StatusTwo)
go

select * from CoffeeTable where AreaId = 5 and (Status = 1 OR Status = 2)
go

create proc GetCoffeeTablesByCustomer
	@CusId int
as
begin
	select ct.TableId, ct.AreaId, ct.TableName, ct.Status from OrderCoffee oc
	INNER JOIN OrderDetail od on oc.OrderId = od.OrderId
	INNER JOIN CoffeeTable ct on od.TableId = ct.TableId
	where CusId = @CusId
end
go

create proc getAllCoffeeTablesWithoutAreId
as
select * from CoffeeTable
go

create proc getAllCoffeeTablesWithoutAreIdByStatus
@Status tinyint
as
select * from CoffeeTable where Status = @Status
go

create proc getCoffeeTableNotSelectedTable
@TableId int,
@AreaId int,
@Status int
as
select * from CoffeeTable where TableId != @TableId and AreaId = @AreaId and Status = @Status
go

create proc getCoffeeTableWithoutCustomer
@AreaId int,
@TableId int
as
select c.* from CoffeeTable c 
inner join OrderDetail od on od.TableId = c.TableId
inner join OrderCoffee oc on oc.OrderId = od.OrderId
where c.TableId != @TableId and c.AreaId = @AreaId and c.Status = 1 and oc.CusId IS NULL and od.Status = 0
go



create proc createNewCustomer
	@Fullname nvarchar(100),
	@PhoneNumber varchar(10),
	@Email varchar(50),
	@Gender bit,
	@ArriveDate Date,
	@ArriveHour Time,
	@Status tinyint /* 0: Khách đã đặt; 1:Khách đã đến; 2:Khách đã thanh toán; 3:Khách không đến; 4:Khách hủy */
as
insert into Customer values(@Fullname,@PhoneNumber,@Email,@Gender,@ArriveDate,@ArriveHour,@Status,CURRENT_TIMESTAMP,null)
go

create proc getAllCustomers
as
select * from Customer
go

create proc getCustomerByEmailAndStatus
@Email varchar(50),
@Status tinyint
as
select * from Customer where Email = @Email and Status = @Status
go

create proc getCustomerByEmail
@Email varchar(50)
as
select * from Customer where Email = @Email
go

create proc getCustomerByCusId
@CusId tinyint
as
select * from Customer where CusId = @CusId
go

create proc getCustomerByTableId
@TableId int,
@Status int
as
select * from Customer c
inner join OrderCoffee oc on oc.CusId = c.CusId
INNER JOIN OrderDetail od on oc.OrderId = od.OrderId
where od.TableId = @TableId and oc.Status = @Status
go

create proc updateStatusCustomer
@CusId int,
@Status tinyint
as
update Customer set Status = @Status where CusId = @CusId
go

create proc updateInfoCustomer
	@Fullname nvarchar(100),
	@PhoneNumber varchar(10),
	@Email varchar(50),
	@Gender bit,
	@ArriveDate Date,
	@ArriveHour Time,
	@Status tinyint,
	@CusId int
as
begin
	update Customer set Fullname = @Fullname, PhoneNumber = @PhoneNumber, Email = @Email, Gender = @Gender, ArriveDate = @ArriveDate, ArriveHour = @ArriveHour, Status = @Status where CusId = @CusId
end
go

create proc deleteCustomersByEmailAndStatus
@Email varchar(50),
@Status tinyint
as
delete from Customer where Email = @Email and Status = @Status
go



create proc createNewOrder
	@UserId int,
	@CusId int
as
	insert into OrderCoffee values(@UserId,NULL,@CusId,0,0,CONVERT(date,CURRENT_TIMESTAMP,101),NULL)
go

create proc createNewOrderWithoutCustomerId
	@UserId int
as
	insert into OrderCoffee values(@UserId,NULL,NULL,0,0,CONVERT(date,CURRENT_TIMESTAMP,101),NULL)
go

create proc getOrderByCusId
@CusId int,
@Status tinyint
as
select * from OrderCoffee where CusId = @CusId and Status = @Status
go

create proc getOrderByOrderId
@OrderId int
as
select * from OrderCoffee where OrderId = @OrderId
go

create proc updateStatusOrderCoffee
@OrderId int,
@Status tinyint
as
update OrderCoffee set Status = @Status where OrderId = @OrderId
go

create proc deleteOrderCoffee
@OrderId int
as
delete from OrderCoffee where OrderId = @OrderId
go

create proc updateTotalMoneyOrderCoffee
@OrderId int,
@TotalMoney float
as
update OrderCoffee set TotalMoney = @TotalMoney where OrderId = @OrderId
go

create proc getOrderCoffeeByDate
@Date date,
@Status tinyint
as
select * from OrderCoffee where CreatedDate = @Date	and Status = @Status	
go

create proc updateUserCashierOrderCoffee
@OrderId int,
@UserCashier int
as
update OrderCoffee set UserCashier = @UserCashier where OrderId = @OrderId
go

create proc getOrderCoffeeBetweenDate
@StartDate date,
@EndDate date,
@Status tinyint
as
select * from OrderCoffee where CreatedDate BETWEEN @StartDate AND @EndDate	AND Status = @Status	
go

create proc searchOrderCoffeeByUserAccount
@Fullname nvarchar(100),
@Status tinyint
as
select oc.* from OrderCoffee oc
inner join UserAccount ua on ua.UserId = oc.UserId
inner join UserAccount uad on uad.UserId = oc.UserCashier
where (ua.Fullname LIKE @Fullname or uad.Fullname like @Fullname) and oc.Status = @Status
go

create proc searchOrderCoffeeByCustomer
@Fullname nvarchar(100),
@Status tinyint
as
select oc.* from OrderCoffee oc
inner join Customer c on c.CusId = oc.CusId
where (c.Fullname LIKE @Fullname) and oc.Status = @Status
go

create proc searchOrderCoffeeByUserAccountDate
@Fullname nvarchar(100),
@Date date,
@Status tinyint
as
select oc.* from OrderCoffee oc
inner join UserAccount ua on ua.UserId = oc.UserId
inner join UserAccount uad on uad.UserId = oc.UserCashier
where (ua.Fullname LIKE @Fullname or uad.Fullname like @Fullname) and oc.Status = @Status and oc.CreatedDate = @Date
go

create proc searchOrderCoffeeByCustomerDate
@Fullname nvarchar(100),
@Date date,
@Status tinyint
as
select oc.* from OrderCoffee oc
inner join Customer c on c.CusId = oc.CusId
where (c.Fullname LIKE @Fullname) and oc.Status = @Status and oc.CreatedDate = @Date
go

create proc searchOrderCoffeeByUserAccountBetweenDate
@Fullname nvarchar(100),
@StartDate date,
@EndDate date,
@Status tinyint
as
select oc.* from OrderCoffee oc
inner join UserAccount ua on ua.UserId = oc.UserId
inner join UserAccount uad on uad.UserId = oc.UserCashier
where (ua.Fullname LIKE @Fullname or uad.Fullname like @Fullname) and oc.Status = @Status and oc.CreatedDate BETWEEN @StartDate AND @EndDate
go

create proc searchOrderCoffeeByCustomerBetweenDate
@Fullname nvarchar(100),
@StartDate date,
@EndDate date,
@Status tinyint
as
select oc.* from OrderCoffee oc
inner join Customer c on c.CusId = oc.CusId
where (c.Fullname LIKE @Fullname) and oc.Status = @Status and oc.CreatedDate BETWEEN @StartDate AND @EndDate
go

create proc getSumTotalMoneyOrderByMonthAndYear
@Month int,
@Year int,
@Status tinyint
as
select SUM(TotalMoney) as sumMoney from OrderCoffee where MONTH(CreatedDate) = @Month and YEAR(CreatedDate) = @Year and Status = @Status
go

create proc updateCreatedDateOrderCoffee
@OrderId int
as
update OrderCoffee set CreatedDate = CONVERT(date,CURRENT_TIMESTAMP,101) where OrderId = @OrderId
go

create proc getOrderCoffeeByUser
@Username varchar(100)
as
select oc.* from OrderCoffee oc
inner join UserAccount ua on ua.UserId = oc.UserId or ua.UserId = oc.UserCashier
where ua.Username = @Username
go






create proc createNewOrderDetail
	@OrderId int,
	@TableId int,
	@Status tinyint
as
insert into OrderDetail values(@OrderId,@TableId,@Status,CURRENT_TIMESTAMP,null)
go

create proc getOrderDetailByTableStatus
@TableId int,
@Status int
as
select * from OrderDetail where TableId = @TableId and Status = @Status
go

create proc updateTableIdOrderDetail
@OldTableId int,
@NewTableId int,
@Status tinyint
as
update OrderDetail set TableId = @NewTableId where TableId = @OldTableId and Status = @Status
go

create proc updateStatusOrderDetail
@OrderDetailId int,
@Status tinyint
as
update OrderDetail set Status = @Status where OrderDetailId = @OrderDetailId
go

create proc deleteOrderDetail
@OrderDetailId int
as
delete from OrderDetail where OrderDetailId = @OrderDetailId
go

create proc getOrderDetailByTable
@TableId int
as
select * from OrderDetail where TableId = @TableId
go

create proc deleteOrderDetailByOrderId
@OrderId int
as
begin
delete from OrderDetail where OrderId = @OrderId
end
go

create proc getOrderDetailsByOrderId
@OrderId int
as
begin
select * from OrderDetail where OrderId = @OrderId
end
go




create proc createNewTableProduct
@ProId int,
@TableId int,
@QuantityOrder int
as
insert into TableProduct values(@ProId,@TableId,@QuantityOrder,0,CURRENT_TIMESTAMP,null)
go

create proc checkExistTableProduct
@ProId int,
@TableId int,
@Status tinyint
as
select * from TableProduct where ProId = @ProId and TableId = @TableId and Status = @Status
go

create proc updateQuantityTableProduct
@TblProId int
as
update TableProduct set QuantityOrder = QuantityOrder + 1 where TblProId = @TblProId
go

create proc updateQuantityTableProductEmpty
@TblProId int
as
update TableProduct set QuantityOrder = 0 where TblProId = @TblProId
go

create proc updateQuantityTableProductClickTable
@TblProId int,
@QuantityOrder int
as
update TableProduct set QuantityOrder = @QuantityOrder where TblProId = @TblProId
go

create proc getTableProductByTableStatus
@TableId int,
@Status int
as
select * from TableProduct where TableId = @TableId and Status = @Status
go

create proc deleteTableProduct
@TblProId int
as
delete from TableProduct where TblProId = @TblProId
go

create proc updatePlusQuantityOrderTableProduct
@TblProId int,
@QuantityOrder int
as
update TableProduct set QuantityOrder = QuantityOrder + @QuantityOrder where TblProId = @TblProId
go

create proc updateSubstractQuantityOrderTableProduct
@TblProId int,
@QuantityOrder int
as
update TableProduct set QuantityOrder = QuantityOrder - @QuantityOrder where TblProId = @TblProId
go

create proc createNewTableProductWithQuantity
@ProId int,
@TableId int,
@QuantityOrder int
as
insert into TableProduct values(@ProId,@TableId,@QuantityOrder,0,CURRENT_TIMESTAMP,null)
go

create proc updateTableIdTableProduct
@OldTableId int,
@NewTableId int
as
update TableProduct set TableId = @NewTableId where TableId = @OldTableId
go

create proc checkProductExistInTableProduct
@ProId int,
@NewTableId int
as
select * from TableProduct where @ProId in (select ProId from TableProduct where TableId = @NewTableId and Status = 0)
go

create proc updateStatusTableProduct
@TableId int,
@Status tinyint
as
update TableProduct set Status = @Status where TableId = @TableId and Status = 0
go

create proc getSumMoneyTableProductByCustomer
@CusId int
as
select sum(tp.QuantityOrder * p.Price) as sumMoney from TableProduct tp
inner join Product p on p.ProId = tp.ProId
where TableId in (select ct.TableId from OrderCoffee oc
	INNER JOIN OrderDetail od on oc.OrderId = od.OrderId
	INNER JOIN CoffeeTable ct on od.TableId = ct.TableId
	where CusId = @CusId) and Status = 0
go

create proc getTableProductByOrderId
@OrderId int
as
select tp.* from TableProduct tp
where tp.Status in (select od.OrderDetailId from OrderDetail od where od.OrderId = @OrderId)
go

create proc getTableProductByProduct
@ProId int
as
select * from TableProduct where ProId = @ProId
go








/*
update CoffeeTable set Status = 0
go
delete from TableProduct
go
delete from OrderDetail
go
delete from OrderCoffee
go
delete from Customer
go

update Customer set Status = 1 where CusId = 25
go

update Customer set ArriveDate = '2020-03-28' where CusId = 24
go

update Customer set ArriveHour = '15:00' where CusId = 25
go

update OrderCoffee set UserCashier = NULL where OrderId = 44
go

update OrderCoffee set Status = 01 where OrderId = 46
go

update CoffeeTable set Status = 2 where TableId = 28
go

update Customer set ArriveDate = '2020-04-01' where CusId = 26
go

delete UserAccount where UserId = 3
go
*/









