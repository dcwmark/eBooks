#!/usr/bin/perl -w

################################
# Set up user agent and proxy. #
################################

use   LWP::UserAgent;
$ua = LWP::UserAgent->new;
$ua->proxy('http', 'http://httpprox:8080');

#######################################################
# Grab the sales rank off the Amazon page and log it.
#
#######################################################

$url      =
"http://www.amazon.com/exec/obidos/ASIN/1565923790/";
$request  = new HTTP::Request('GET', "$url");
$response = $ua->request($request);
$_        = $response->content;
m|Amazon.com Sales Rank: </b>\s*(\d*),*(\d+)\s|s && do
{
    open(FH, ">>wpt.sales") || die "Could not open
wpt.sales\n";
    $date = `date +'%m %d %H %M %S %Y'`;
    chop $date;
    printf FH "%s %s\n", $date, $1.$2;
    close(FH);
};
    
#########################
# Regenerate the graph. #
#########################
    
$gnuplot_cmd = qq|
    set term gif
    set output "sales.gif"
    set xdata time
    set ylabel "Amazon sales rank"
    set bmargin 3
    set logscale y
    set yrange [1:30000] reverse
    set timefmt "%m %d %H %M %S %Y"
    plot "wpt.sales" using 1:7 title "web performance
tuning" with lines
|;

open(GP, "|/usr/local/bin/gnuplot");
print GP $gnuplot_cmd;
close(GP);

It works really well and makes nice graphs. It would
be much harder in Java.

