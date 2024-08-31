
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${listC}" var="o">
            
                 <c:url  var="editURL" value="/category">
                   <c:param name="cid" value="${o.cid}"/>
                 
                 </c:url>
                 
                <li class="list-group-item text-white ${tag==o.cid ?  "active" : "" }"><a href="${editURL}">${o.cname}</a></li>
            </c:forEach>

        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Last product</div>
        <div class="card-body">
            <img class="img-fluid" src="https://i.ebayimg.com/images/g/C0AAAOSwCz5mNjQd/s-l960.webp" />
            <h5 class="card-title">Giày đi chơi tết</h5>
            <p class="card-text">Comfortable Sports Shoes Men Athletic Outdoor Cushioning Sneakers for Walking&Jogging (EUR Size 39-48)</p>
            <p class="bloc_left_price">100 $</p>
        </div>
    </div>
    
</div>