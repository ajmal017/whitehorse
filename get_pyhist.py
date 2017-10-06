#! /usr/bin/env python3
# coding=utf-8
"""
Some examples of how to use the library.

Run first with no options to see the usage message.
Then try with different options to see that different functionality. Not all
library functionality is used in this file. Look at conn.py and listeners.py
for more details.
"""

import argparse
import datetime
import pyiqfeed as iq
import time
from localconfig import dtn_product_id, dtn_login, dtn_password


def get_hist(symbol, interval, maxdatapoints,datadirection=0,requestid='',datapointspersend='',intervaltype=''):
    #get_bitstampfeed()
    global feed
    global ohlc
    
    symbol=symbol.upper()
    instrument_list=Instrument.search().filter('term',**{'sym.raw':symbol}).execute()
    if instrument_list and len(instrument_list) > 0:
        instrument=instrument_list[0]
    else:
        instrument=Instrument()
        instrument.sym=symbol
        instrument.save()
    
    from pandas.io.json import json_normalize
    
    feed_list=Feed.search().filter('term',frequency=interval).filter('term',instrument_id=instrument.id).sort('-date')
    feed_list=feed_list[:int(maxdatapoints)]
    res=[]
    for feed in feed_list:
        #feed.date=eastern.localize(feed.date,is_dst=True)
        date=datetime(feed.date.year,feed.date.month,feed.date.day,feed.date.hour,feed.date.minute,feed.date.second)
        quote={ 'Date':date,
                                'Open':feed.open,
                                'High':feed.high,
                                'Low':feed.low,
                                'Close':feed.close,
                                'Volume':feed.volume
                            }
        if date <= datetime.now():
            res.append(quote)
    data = json_normalize(res)
    data=data.set_index('Date')
    #data.to_csv('test.csv')
    return data
    

def get_realtime_hist(symbol, interval, maxdatapoints,datadirection=0,requestid='',datapointspersend='',intervaltype=''):
    #get_bitstampfeed()
    global feed
    global ohlc
    
    symbol=symbol.upper()
    instrument_list=Instrument.search().filter('term',**{'sym.raw':symbol}).execute()
    if instrument_list and len(instrument_list) > 0:
        instrument=instrument_list[0]
        pass #print  instrument.id, symbol
    else:
        instrument=Instrument()
        instrument.sym=symbol
        instrument.save()
    
    bg_get_hist(instrument, symbol, interval, maxdatapoints,datadirection,requestid,datapointspersend,intervaltype)
    
    
    from pandas.io.json import json_normalize
    
    feed_list=Feed.search().filter('term',frequency=interval).filter('term',instrument_id=instrument.id).sort('-date')
    feed_list=feed_list[:int(maxdatapoints)]
    res=[]
    for feed in feed_list:
        #feed.date=eastern.localize(feed.date,is_dst=True)
        date=datetime(feed.date.year,feed.date.month,feed.date.day,feed.date.hour,feed.date.minute,feed.date.second)
        quote={ 'Date':datetime(feed.date.year,feed.date.month,feed.date.day,feed.date.hour,feed.date.minute,feed.date.second),
                               'Open':feed.open,
                                'High':feed.high,
                                'Low':feed.low,
                                'Close':feed.close,
                                'Volume':feed.volume
                            }
        if date <= datetime.now():
            res.append(quote)
    data = json_normalize(res)
    data=data.set_index('Date')
    #data.to_csv('test.csv')
    return data



def get_level_1_quotes_and_trades(ticker: str, seconds: int):
    """Get level 1 quotes and trades for ticker for seconds seconds."""

    quote_conn = iq.QuoteConn(name="pyiqfeed-Example-lvl1")
    quote_listener = iq.VerboseQuoteListener("Level 1 Listener")
    quote_conn.add_listener(quote_listener)
    with iq.ConnConnector([quote_conn]) as connector:
        all_fields = sorted(list(iq.QuoteConn.quote_msg_map.key
        print("NAIC Codes:")
        print(table_conn.get_naic_codes())
        print("")
        table_conn.remove_listener(table_listener)

def get_live_feed_to_db(ticker: str, bar_len: int, bar_unit: str,
                            num_bars: int):s()))
        quote_conn.select_update_fieldnames(all_fields)
        quote_conn.watch(ticker)
        time.sleep(seconds)
        quote_conn.unwatch(ticker)
        quote_conn.remove_listener(quote_listener)


def get_historical_bar_data(ticker: str, bar_len: int, bar_unit: str,
                            num_bars: int):
    """Shows how to get interval bars."""
    hist_conn = iq.HistoryConn(name="pyiqfeed-Example-historical-bars")
    hist_listener = iq.VerboseIQFeedListener("History Bar Listener")
    hist_conn.add_listener(hist_listener)

    with iq.ConnConnector([hist_conn]) as connector:
        # look at conn.py for request_bars, request_bars_for_days and
        # request_bars_in_period for other ways to specify time periods etc
        try:
            bars = hist_conn.request_bars(ticker=ticker,
                                          interval_len=bar_len,
                                          interval_type=bar_unit,
                                          max_bars=num_bars)
            print(bars)

            today = datetime.date.today()
            start_date = today - datetime.timedelta(days=10)
            start_time = datetime.datetime(year=start_date.year,
                                           month=start_date.month,
                                           day=start_date.day,
                                           hour=0,
                                           minute=0,
                                           second=0)
            end_time = datetime.datetime(year=today.year,
                                         month=today.month,
                                         day=today.day,
                                         hour=23,
                                         minute=59,
                                         second=59)
            bars = hist_conn.request_bars_in_period(ticker=ticker,
                                                    interval_len=bar_len,
                                                    interval_type=bar_unit,
                                                    bgn_prd=start_time,
                                                    end_prd=end_time)
            print(bars)
        except (iq.NoDataError, iq.UnauthorizedError) as err:
            print("No data returned because {0}".format(err))

        print("Trade Conditions:")
        print(table_conn.get_trade_conditions())
        print("")

        print("SIC Codes:")
        print(table_conn.get_sic_codes())
        print("")

def get_historical_bar_to_db(ticker: str, bar_len: int, bar_unit: str,
                            num_bars: int):
    """Shows how to get interval bars."""
    hist_conn = iq.HistoryConn(name="pyiqfeed-Example-historical-bars")
    hist_listener = iq.VerboseIQFeedListener("History Bar Listener")
    hist_conn.add_listener(hist_listener)
    """Shows how to get interval bars."""
    hist_conn = iq.HistoryConn(name="pyiqfeed-Example-historical-bars")
    hist_listener = iq.VerboseIQFeedListener("History Bar Listener")
    hist_conn.add_listener(hist_listener)

    def document():
        with iq.ConnConnector([hist_conn]) as connector:
            # look at conn.py for request_bars, request_bars_for_days and
            # request_bars_in_period for other ways to specify time periods etc
            try:
                bars = hist_conn.request_bars(ticker=ticker,
                                              interval_len=bar_len,
                                              interval_type=bar_unit,
                                              max_bars=num_bars)
                print(bars)



                today = datetime.date.today()
                start_date = today - datetime.timedelta(minutes=10)
                start_time = datetime.datetime(year=start_date.year,
                                               month=start_date.month,
                                               day=start_date.day,
                                               hour=0,
                                               minute=0,
                                               second=0)
                end_time = datetime.datetime(year=today.year,
                                             month=today.month,
                                             day=today.day,
                                             hour=23,
                                             minute=59,
                                             second=59)
                bars = hist_conn.request_bars_in_period(ticker=ticker,
                                                        interval_len=bar_len,
                                                        interval_type=bar_unit,
                                                        bgn_prd=start_time,
                                                        end_prd=end_time)
                for bar in bars:
                    feed={  'instrument_id':instrument.id,
                                                'frequency' : frequency,
                                                'date':date,
                                                'open': quote['Open'],
                                                'high': quote['High'],
                                                'low': quote['Low'],
                                                'close': quote['Close'],
                                                'volume': quote['Volume']
                                            }
                    bar_list=Feed.search().filter('term',date=date).filter('term',instrument_id=instrument.id).filter('term',frequency=frequency)
                    if bar_list and bar_list.count() > 0:
                            if i == 1:
                                pass #print  'update', symbol
                                mydoc=bar_list.execute()[0]._id
                                yield es.update_op(doc=feed,
                                   id=mydoc, 
                                   index='beginning',
                                   doc_type='feed',
                                   doc_as_upsert=True)
                    else:
                        pass #print  'insert', symbol
                        yield es.index_op(feed)
                print(bars)

            except (iq.NoDataError, iq.UnauthorizedError) as err:
                print("No data returned because {0}".format(err))

        
    for chunk in bulk_chunks(documents(),
                         docs_per_chunk=500,
                         bytes_per_chunk=10000):
                            # We specify a default index and doc type here so we don't
                            # have to repeat them in every operation:
                            es.bulk(chunk, doc_type='feed', index='beginning')                                

get_historical_bar_to_db(ticker="SPY",
                        bar_len=300,
                        bar_unit='s',
                        num_bars=100)

get_historical_bar_data(ticker="SPY",
                        bar_len=300,
                        bar_unit='s',
                        num_bars=100)