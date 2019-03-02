# jOrgChart

This plugin allows you to draw a tree with the distribution of child nodes in two modes. The first mode is a traditional (horizontal) - sibling nodes are placed in a horizontal row, and a second mode (vertical) - all subsequent nodes are arranged in a vertical row.

Based on the work of Wes Nolte (without drag and drops mode)
https://github.com/wesnolte/jOrgChart
And based on the work of Dmitry Sinyavsky (css - rules for vertical nodes)
http://habrahabr.ru/post/55753/

Horizontal -default- mode view example:
<img alt="jQuery jOrgChart plugin (horizontal -default- mode)" title="jQuery jOrgChart plugin (horizontal -default- mode)" src="https://00c51259-a-62cb3a1a-s-sites.googlegroups.com/site/mozg1984/download/Tree-horizontal.png?attachauth=ANoY7cpNYmA05mF14xCB8tPyaa5qW6UszIod4lTUlLm5QR0nsMs8BLv2LclKv-MQBMslKtLRQznaTgdKZfKcnI_aLOaG4MYYEKyMWzQT-E2c48ZZShodhUo3KmQmFl-zpFuV3j4Hc1tIl3ibAkhXqeK8QYr5-OZ_xSwBmRs66hBZvowW9EmfCGh3jQHydDDNI1kevWYphv-9F4gZuxIwTcl5nQFD0RtXRw1xQjZqAIWry-8juE0gTDY%3D&attredirects=0"/>

Vertical mode view example:
<img alt="jQuery jOrgChart plugin (vertical mode)" title="jQuery jOrgChart plugin (vertical mode)" src="https://00c51259-a-62cb3a1a-s-sites.googlegroups.com/site/mozg1984/download/Tree-vertical.png?attachauth=ANoY7crrh3O2tRh-700beLKv5zAc3pujKYjooLujWgGEsHGJD5Zr3E1NgpRBcQT8miERenruB8ytgNp8aocvKC_AF02A5BMNmxt2Lry3bOGfI3Vs5pjat2jRC3w4IqAQUH4n3NYyPsGXWgN9tABhbxQ5s1ZMdML0SCa7OBWBvr2wLS-BokEDM0HsQimAhOakqn1SHYixOjMQDSJ0RhvyU1BvMYUhAKLmwpEJXG9hoqJAqErdXxgb2HE%3D&attredirects=0"/>

#
1. include jquery library
2. include jOrgChart library
3. call jquery jOrgChart method whith parameters:
<ul>
<li>chartClass - added to placeholder for render org chart tree (default - "jOrgChart");</li>
<li>chartElement - placeholder for render org chart tree;</li>
<li>nodeClicked - node click handler (this or node refer to clicked node, type parameter takes only two values - 'horizontal' or 'vertical');</li>
<li>depth - max level of hierarchy (integer);</li>
</ul>

Default type of render mode is horizontal. If you need to change it to vertical add attribute in &lt;ul&gt; container - type="vertical". An additional requirement to the ul-li list the each last &lt;li&gt; elements must be complement by class "last" (&lt;li class="last"&gt;).   

<pre>
// ...in your project

var params = {
  // placeholder for render org chart tree
  chartElement: $("#tree-view"),
  
  // lighting a node in the selection
  nodeClicked: nodeClicked(node, type) {
    	if (type == 'horizontal') {}  // horizontal mode of render
    	if (type == 'vertical') {}    // vertical mode of render
    	node = node || $(this);
    	$('.jOrgChart .selected').removeClass('selected');
    	node.addClass('selected');
    }
  }
};

// ul li source of org chart tree
$("#tree-data").jOrgChart(params);
</pre>

#Notice
This plugin was created in a short time to solve the urgent task. Therefore cross-browser compatibility has not been tested. 

#License 
Under the MIT and GPL licenses.
