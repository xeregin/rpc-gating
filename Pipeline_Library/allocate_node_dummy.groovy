// if the function is called call, then when this script is loaded using the
// load step, the result will be directly callable.
def call(Map args){
  // the load step can be used, but only within the function that is called by the pipeline
  def rpc_common = load 'Pipeline_Library/common.groovy'
  nodes=[]
  // This is the groovy way but "each" doesn't work in Jenkins: (1..args.numnodes).each {
  // https://issues.jenkins-ci.org/browse/JENKINS-26699
  for (i=1; i<=args.numnodes; i++){
    // contrived example of using a shared lib function:
    nodename = rpc_common.nodename(nodeprefix: args.nodeprefix, nodenum: i)
    sh "echo 'Dummy Node Allocation: ${nodename}'"
    // store nodes list for use in future steps (Eg cleanup)
    nodes.push(nodename)
  }
  return nodes
}

// all groovy scripts get compiled into classes. In order to be importable
/// scripts have  to return "this" which is the current class
return this;
